package com.picpay.picpay.DTOs;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroConta (
    Long id, 
    @NotBlank
    String name, 

    String senha,
    
    String cpf,

    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4}-\\d{4}")
    String telefone,

    String numero_conta, 
    
    BigDecimal saldo,

    Tipo_conta tipoConta,

    @Valid
    DadosEndereco endereco
) {

}
