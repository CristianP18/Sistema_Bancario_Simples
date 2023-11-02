package com.picpay.picpay.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosDeposito (
    Long id, String numero_conta, String name, String cpf, String telefone, BigDecimal valor_deposito, LocalDateTime data
){
    
}
