package com.picpay.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.picpay.picpay.DTOs.DadosDeposito;
import com.picpay.picpay.domain.conta.ContaRepository;
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
    private ContaRepository contaRepository;

    @Autowired
    private EfetuarDeposito efetuarDeposito;



    @PostMapping
    @Transactional
    public ResponseEntity<Deposito> cadastrar(@RequestBody @Valid DadosDeposito dados, UriComponentsBuilder uriBuider ) {
        efetuarDeposito.efetuarDeposito(dados); 
        Deposito result = new Deposito(dados); 
        repository.save(result);
        var uri = uriBuider.path("/depositos/{id}").buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(uri).body(result);
    }


    @GetMapping
    public ResponseEntity<Object> listar() {
        var page = repository.findAll();
        return ResponseEntity.ok(page);
    }
    
}
