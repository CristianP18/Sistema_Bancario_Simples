package com.picpay.picpay.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.picpay.DTOs.DadosDeposito;
import com.picpay.picpay.domain.conta.Conta;
import com.picpay.picpay.domain.conta.ContaRepository;

import jakarta.validation.ValidationException;

@Service
public class EfetuarDeposito {
    
    private final ContaRepository repository;

    @Autowired
    public EfetuarDeposito(ContaRepository repository) {
        this.repository = repository;
    }

    public void efetuarDeposito(DadosDeposito dados) {
        Optional<Conta> contaDestino = repository.findByNumeroConta(dados.numero_conta());
        if (!contaDestino.isPresent()) {
            throw new ValidationException("Conta não encontrada.");
        }

        BigDecimal valorDeposito = dados.valor_deposito();
        BigDecimal zero = BigDecimal.ZERO;

        if (valorDeposito.compareTo(zero) <= 0) {
            throw new ValidationException("O valor do depósito deve ser maior que zero.");
        }

        Conta conta = contaDestino.get();
        BigDecimal novoSaldo = conta.getSaldo().add(valorDeposito);
        conta.setSaldo(novoSaldo);
        repository.save(conta);
    }
}
