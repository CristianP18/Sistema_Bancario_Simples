package com.picpay.picpay.domain.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpay.picpay.DTOs.DadosTransacao;

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

@Table(name="transacao")
@Entity(name="Transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroContaSend;
    private String numeroContaReceive;
    private LocalDateTime data;
    private BigDecimal valor_transacao;


    public Transacao(DadosTransacao dados){
        this.numeroContaSend = dados.numeroContaSend();
        this.numeroContaReceive = dados.numeroContaReceive();
        this.data = LocalDateTime.now();
        this.valor_transacao = dados.valor_transacao();    
    }
    
}
