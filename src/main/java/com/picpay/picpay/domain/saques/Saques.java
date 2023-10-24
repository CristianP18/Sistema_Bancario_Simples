package com.picpay.picpay.domain.saques;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpay.picpay.DTOs.DadosSaques;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="saques")
@Entity(name="Saques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Saques {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String numero_conta;
    private String senha;
    private BigDecimal valor_saque;
    private LocalDateTime data;

    public Saques(DadosSaques dados){
        this.id = dados.id();
        this.numero_conta = dados.numero_conta();
        this.senha = dados.senha();
        this.valor_saque = dados.valor_saque();
        this.data = LocalDateTime.now();
    }
    
}
