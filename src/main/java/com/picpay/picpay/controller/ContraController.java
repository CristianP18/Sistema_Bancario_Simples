package com.picpay.picpay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.picpay.DTOs.AtualizarDadosConta;
import com.picpay.picpay.DTOs.DadosCadastroConta;
import com.picpay.picpay.domain.conta.Conta;
import com.picpay.picpay.domain.conta.ContaRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContraController {

    @Autowired
    private ContaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Conta> cadastra(@RequestBody @Valid DadosCadastroConta dados){
        Conta result = repository.save(new Conta(dados));
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public List<Conta> listar(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Conta>> listarPotId(@PathVariable Long id){
        Optional<Conta> conta = repository.findById(id);
        return ResponseEntity.ok(conta);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Conta> atualizarDadosConta(@RequestBody @Valid AtualizarDadosConta dados, @PathVariable Long id){
        Optional<Conta>conta = repository.findById(id);
        if(conta.isPresent()){
          Conta atualizaConta = conta.get();
          atualizaConta.atualizarDadosConta(dados);
          repository.save(atualizaConta);

          return ResponseEntity.ok(atualizaConta);
        }else{
          return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Conta> desabilitar(@PathVariable Long id) {
        var conta = repository.getReferenceById(id);
        conta.excluir();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
        System.out.println("Conta Deletada");
    }
    
}
