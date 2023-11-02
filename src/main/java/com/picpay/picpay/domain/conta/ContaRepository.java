package com.picpay.picpay.domain.conta;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ContaRepository extends JpaRepository<Conta, Long>{
    @Query("SELECT c FROM Conta c WHERE c.ativo = true")
    Page<Conta> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT c FROM Conta c WHERE c.numero_conta = :numeroContaSend")
    Optional<Conta> findByNumeroContaSend(String numeroContaSend);

    @Query("SELECT c FROM Conta c WHERE c.numero_conta = :numeroContaReceive")
    Optional<Conta> findByNumeroContaReceive( String numeroContaReceive);
    @Query("SELECT c FROM Conta c WHERE c.numero_conta = :numero_conta")
    Optional<Conta> findByNumeroConta(String numero_conta);

  



}
