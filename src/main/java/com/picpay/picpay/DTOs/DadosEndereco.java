package com.picpay.picpay.DTOs;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco (
    @NotBlank
    String cidade,
    @NotBlank
    String bairro,
    @NotBlank
    String cep,
    @NotBlank
    String numero
) {


}
