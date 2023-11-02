package com.picpay.picpay.domain.conta;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.picpay.picpay.DTOs.DadosCadastroContaTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@DataJpaTest
@ActiveProfiles("test")
class ContaRepositoryTest {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void findAllByAtivoTrue() {
        // Crie uma conta ativa
        Conta contaAtiva = createConta(true, "12345");
        // Crie uma conta inativa
        createConta(false, "67890");

        Page<Conta> contasAtivas = repository.findAllByAtivoTrue(PageRequest.of(0, 10));

        assertThat(contasAtivas).isNotEmpty();
        assertThat(contasAtivas.getContent()).contains(contaAtiva);
    }

    @Test
    void findByNumeroContaSend() {
        String numeroContaSend = "1111";
        Conta conta = createConta(true, numeroContaSend);
        Optional<Conta> found = repository.findByNumeroContaSend(numeroContaSend);

        assertTrue(found.isPresent());
        assertThat(found.get()).isEqualTo(conta);
    }

    @Test
    void findByNumeroContaReceive() {
        String numeroConta = "123";
        Conta conta = createConta(true, numeroConta);
        Optional<Conta> result = repository.findByNumeroContaReceive(numeroConta);
        assertTrue(result.isPresent());
        assertThat(result.get()).isEqualTo(conta);
    }

    @Test
    @Transactional
    void findByNumeroConta() {
        String numeroConta = "1212";
        Conta conta = createConta(true, numeroConta);

        TypedQuery<Conta> query = em.createQuery("SELECT c FROM Conta c WHERE c.numeroConta = :numeroConta", Conta.class);
        query.setParameter("numeroConta", numeroConta);
        List<Conta> result = query.getResultList();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(conta);
    }

    private Conta createConta(boolean ativo, String numeroConta) {
        DadosCadastroContaTest data = new DadosCadastroContaTest(1L, ativo, "terre", "123", "99999901", "54996227442", numeroConta, new BigDecimal(200));
        Conta conta = new Conta(data);
        em.persist(conta);
        return conta;
    }

}
