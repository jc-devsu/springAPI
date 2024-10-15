package com.devsu.cuenta_movimientos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.cuenta_movimientos.model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
        List<Movimiento> findByCuentaClienteIdAndFechaBetween(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);

}