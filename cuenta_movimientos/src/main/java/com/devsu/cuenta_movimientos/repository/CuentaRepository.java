package com.devsu.cuenta_movimientos.repository;

import com.devsu.cuenta_movimientos.model.Cuenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClienteId(Long clienteId);

}
