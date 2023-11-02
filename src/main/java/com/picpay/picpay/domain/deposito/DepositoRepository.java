package com.picpay.picpay.domain.deposito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DepositoRepository extends JpaRepository<Deposito, Long>{
    Page<Deposito> findAll(Pageable paginacao);
}
