package com.picpay.picpay.domain.deposito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpay.picpay.DTOs.DadosDeposito;

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

@Table(name="depositos")
@Entity(name="Deposito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String numero_conta;
    private String name;
    private String cpf;
    private String telefone;
    private BigDecimal valor_deposito;
    private LocalDateTime data;
    

    public Deposito(DadosDeposito dados){
        this.id = dados.id();
        this.numero_conta = dados.numero_conta();
        this.name = dados.name();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.valor_deposito = dados.valor_deposito();
        this.data = LocalDateTime.now();
    }
}
