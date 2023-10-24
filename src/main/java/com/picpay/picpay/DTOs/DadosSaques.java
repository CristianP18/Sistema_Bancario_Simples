package com.picpay.picpay.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosSaques ( 
    Long id, String numero_conta, String senha, BigDecimal valor_saque, LocalDateTime data
){
    
}
