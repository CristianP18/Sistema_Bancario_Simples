package com.picpay.picpay.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AtualizarDadosConta (
    @NotBlank
    String name, 
    @NotBlank
    String senha,
    @NotBlank
    String telefone,
    @NotBlank 
    DadosEndereco endereco
) {



}
