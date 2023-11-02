package com.picpay.picpay.domain.deposito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosListarDeposito (
    Long id, String numero_conta, String name, String cpf, String telefone, BigDecimal valor_deposito, LocalDateTime data 
){
    public DadosListarDeposito(Deposito deposito){
        this(deposito.getId(), deposito.getNumero_conta(), deposito.getName(), deposito.getCpf(), deposito.getTelefone(), deposito.getValor_deposito(), deposito.getData());
    }
}
