package com.picpay.picpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.picpay.DTOs.DadosSaques;
import com.picpay.picpay.domain.saques.Saques;
import com.picpay.picpay.domain.saques.SaquesRepository;
import com.picpay.picpay.service.EfetuarSaque;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/saques")
public class SaquesController {

    @Autowired
    private EfetuarSaque efetuarSaque;

    @Autowired
    private SaquesRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity<Saques> cadastrar(@RequestBody @Valid DadosSaques dados) {
        efetuarSaque.efetuarSaque(dados);
        Saques resul = repository.save(new Saques(dados));
        return ResponseEntity.ok(resul);
    }

    @GetMapping
    public ResponseEntity<List<Saques>> listar(){
        var result = repository.findAll();
        return ResponseEntity.ok(result);
    }
}
