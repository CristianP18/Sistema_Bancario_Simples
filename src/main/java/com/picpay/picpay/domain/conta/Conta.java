package com.picpay.picpay.domain.conta;

import java.math.BigDecimal;

import com.picpay.picpay.DTOs.AtualizarDadosConta;
import com.picpay.picpay.DTOs.DadosCadastroConta;
import com.picpay.picpay.DTOs.DadosCadastroContaTest;
import com.picpay.picpay.DTOs.Tipo_conta;
import com.picpay.picpay.domain.endereco.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="contas")
@Entity(name="Conta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String senha;

    private String cpf;

    private String telefone;

    private String numero_conta;
    
    private BigDecimal saldo;

    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private Tipo_conta tipoConta;
    @Embedded
    private Endereco endereco;

    public Conta(DadosCadastroConta dados){
        this.ativo = true;
        this.name = dados.name();
        this.senha = dados.senha();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.numero_conta = dados.numero_conta();
        this.saldo = dados.saldo();
        this.tipoConta = dados.tipoConta();
        this.endereco = new Endereco(dados.endereco());

    }

    public Conta(DadosCadastroContaTest data) {
        this.ativo = true;
        this.name = data.name();
        this.senha = data.senha();
        this.cpf = data.cpf();
        this.telefone = data.telefone();
        this.numero_conta = data.numero_conta();
        this.saldo = data.saldo();
    }

    public Conta atualizarDadosConta(AtualizarDadosConta dados){
        this.name = dados.name();
        this.senha = dados.senha();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
        return this;
    }
   
    
    public void excluir() {
        this.ativo = false;
        
        
    }
    public boolean isPresent() {
        return false;
    }
    
}
