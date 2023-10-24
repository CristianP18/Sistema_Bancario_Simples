package com.picpay.picpay.domain.conta;



import com.picpay.picpay.DTOs.DadosEndereco;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Embeddable
public class Endereco {
     
    public String cidade;
    public String bairro;
    public String cep;
    public String numero;

    public Endereco(DadosEndereco dados){
        this.cidade = dados.cidade();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();

    }
}
