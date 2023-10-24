package com.picpay.picpay.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.picpay.DTOs.DadosSaques;
import com.picpay.picpay.domain.conta.Conta;
import com.picpay.picpay.domain.conta.ContaRepository;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Service
public class EfetuarSaque {

    @Autowired
    private ContaRepository repository;

    public void efetuarSaque(@Valid DadosSaques dados) {

        Optional<Conta> conta = repository.findByNumeroConta(dados.numero_conta());

        if(!conta.isPresent()){
            throw new ValidationException("Conta não encontrada!");
        }
        if(!conta.get().getSenha().equals(dados.senha())){
            throw new ValidationException("Senha incorreta");
        }

        BigDecimal novoSaldo = conta.get().getSaldo().subtract(dados.valor_saque());
        conta.get().setSaldo(novoSaldo);
        repository.save(conta.get());
        
    }
    
}
