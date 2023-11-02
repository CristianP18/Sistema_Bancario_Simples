package com.picpay.picpay.DTOs;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroConta (
    Long id,
    boolean ativo,
    @NotBlank
    String name, 
    @NotBlank
    String senha,
    @NotBlank
    String cpf,

    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4}-\\d{4}")
    String telefone,
    @NotBlank
    String numero_conta, 
    @NotNull
    BigDecimal saldo,
   
    Tipo_conta tipoConta,

    @Valid
    DadosEndereco endereco
) {

}
