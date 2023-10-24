package com.picpay.picpay.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picpay.picpay.DTOs.DadosTransacao;
import com.picpay.picpay.domain.conta.Conta;
import com.picpay.picpay.domain.conta.ContaRepository;

import jakarta.validation.ValidationException;

@Service
public class EfetuarTransacao {

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public void efetuarTransacao(DadosTransacao dados) {
        Optional<Conta> sendConta = contaRepository.findByNumeroContaSend(dados.numeroContaSend());
        Optional<Conta> receiveConta = contaRepository.findByNumeroContaReceive(dados.numeroContaReceive());

        if (!sendConta.isPresent()) {
            throw new ValidationException("Conta de envio não encontrada!");
        }

        if (!receiveConta.isPresent()) {
            throw new ValidationException("Conta de recebimento não encontrada!");
        }

        BigDecimal valorTransacao = dados.valor_transacao();
        BigDecimal sendSaldo = sendConta.get().getSaldo();

        if (sendSaldo.compareTo(valorTransacao) < 0) {
            throw new ValidationException("Saldo insuficiente na conta de envio.");
        }

        sendSaldo = sendSaldo.subtract(valorTransacao);
        BigDecimal receiveSaldo = receiveConta.get().getSaldo().add(valorTransacao);

        sendConta.get().setSaldo(sendSaldo);
        receiveConta.get().setSaldo(receiveSaldo);

        // Salvar as contas atualizadas no repositório
        contaRepository.save(sendConta.get());
        contaRepository.save(receiveConta.get());
    }
}
