package com.picpay.picpay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.picpay.picpay.DTOs.DadosTransacao;
import com.picpay.picpay.domain.transacao.Transacao;
import com.picpay.picpay.domain.transacao.TransacaoRepository;
import com.picpay.picpay.service.EfetuarTransacao;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private EfetuarTransacao efetuarTransacao;

    @PostMapping
    @Transactional
    public ResponseEntity<Transacao> gerarTransacao(@RequestBody @Valid DadosTransacao dados, UriComponentsBuilder uriBuilder ){
        Transacao result = new Transacao(dados);
        repository.save(result);
        efetuarTransacao.efetuarTransacao(dados);
        var uri = uriBuilder.path("/transacao/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping
    public List<Transacao> listar(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Transacao>> listaPoId(@PathVariable Long id){
        Optional<Transacao> transacao = repository.findById(id);
        return ResponseEntity.ok(transacao);
    }
    
}
