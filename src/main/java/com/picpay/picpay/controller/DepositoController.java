package com.picpay.picpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.picpay.DTOs.DadosDeposito;
import com.picpay.picpay.domain.deposito.Deposito;
import com.picpay.picpay.domain.deposito.DepositoRepository;
import com.picpay.picpay.service.EfetuarDeposito;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/depositos")
public class DepositoController {

    @Autowired
    private DepositoRepository repository;

    @Autowired
    private EfetuarDeposito efetuarDeposito;



    @PostMapping
    @Transactional
    public ResponseEntity<Deposito> cadastrar(@RequestBody @Valid DadosDeposito dados) {
        efetuarDeposito.efetuarDeposito(dados); 
        Deposito result = repository.save(new Deposito(dados));
        return ResponseEntity.ok(result);
    }


    @GetMapping
    public ResponseEntity<List<Deposito>> listar(){
        repository.findAll();
        return ResponseEntity.ok().build();
    }
    
}
