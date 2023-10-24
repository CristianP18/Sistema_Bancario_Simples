package com.picpay.picpay.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosTransacao (
    Long id, 

    String numeroContaSend,

    String numeroContaReceive,
    
    LocalDateTime data,
    @NotNull
    BigDecimal valor_transacao
){
    
}
