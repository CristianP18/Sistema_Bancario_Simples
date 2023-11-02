package com.picpay.picpay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Saques> cadastrar(@RequestBody @Valid DadosSaques dados, UriComponentsBuilder uriBuilder) {
        efetuarSaque.efetuarSaque(dados);
        Saques resul = new Saques(dados);
        repository.save(resul);
        var uri = uriBuilder.path("/saques/{id}").buildAndExpand(resul.getId()).toUri();
        return ResponseEntity.created(uri).body(resul);
    }

    @GetMapping
    public ResponseEntity<List<Saques>> listar(){
        var result = repository.findAll();
        return ResponseEntity.ok(result);
    }
}
