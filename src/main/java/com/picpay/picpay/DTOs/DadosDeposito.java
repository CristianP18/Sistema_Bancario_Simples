package com.picpay.picpay.DTOs;

import java.math.BigDecimal;

public record DadosDeposito (
    Long id, String numero_conta, String name, String cpf, String telefone, BigDecimal valor_deposito
){
    
}
