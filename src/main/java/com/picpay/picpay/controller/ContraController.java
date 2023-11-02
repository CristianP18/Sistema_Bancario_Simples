package com.picpay.picpay.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.picpay.picpay.DTOs.AtualizarDadosConta;
import com.picpay.picpay.DTOs.DadosCadastroConta;
import com.picpay.picpay.DTOs.DadosListagemContas;
import com.picpay.picpay.domain.conta.Conta;
import com.picpay.picpay.domain.conta.ContaRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/contas")
public class ContraController {

    @Autowired
    private ContaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Conta> cadastra(@RequestBody @Valid DadosCadastroConta dados, UriComponentsBuilder uriBuilder){
        Conta result = new Conta(dados);
        repository.save(result);

        var uri = uriBuilder.path("/contas/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }
    @GetMapping
    public ResponseEntity<Object> listar(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemContas::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity listarPotId(@PathVariable Long id){
        var conta = repository.findById(id);
        if(conta.isPresent()){
            return ResponseEntity.ok(conta);
        }else{
            throw new ValidationException("Conta não Existe");
        }
        
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Conta> atualizarDadosConta(@RequestBody @Valid AtualizarDadosConta dados, @PathVariable Long id){
        Optional<Conta> conta = repository.findById(id);
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
