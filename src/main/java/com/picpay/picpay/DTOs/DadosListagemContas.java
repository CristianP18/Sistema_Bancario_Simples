package com.picpay.picpay.DTOs;

import com.picpay.picpay.domain.conta.Conta;

public record DadosListagemContas (Long id, Boolean ativo, String name, String cpf, String telefone,
            String numero_conta, Tipo_conta tipoConta){
    public DadosListagemContas(Conta conta){
        this(conta.getId(), conta.getAtivo(),conta.getName(), conta.getCpf(),conta.getTelefone(), conta.getNumero_conta(), conta.getTipoConta());
    }

  
    
}
