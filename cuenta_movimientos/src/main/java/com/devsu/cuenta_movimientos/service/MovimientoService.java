package com.devsu.cuenta_movimientos.service;
import com.devsu.cuenta_movimientos.model.Cuenta;
import com.devsu.cuenta_movimientos.model.Movimiento;
import com.devsu.cuenta_movimientos.repository.CuentaRepository;
import com.devsu.cuenta_movimientos.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public Movimiento registrarMovimiento(Movimiento movimiento) throws Exception {
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(movimiento.getCuenta().getId());
        if (cuentaOpt.isPresent()) {
            Cuenta cuenta = cuentaOpt.get();
            double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
            if (nuevoSaldo < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo no disponible");
            }
            LocalDate fechaActual = LocalDate.now();

            cuenta.setSaldoInicial(nuevoSaldo);
            movimiento.setFecha(fechaActual);
            movimiento.setSaldo(nuevoSaldo);
            movimiento.setCuenta(cuenta);
            movimiento.setEstado(true);
            return movimientoRepository.save(movimiento);
        } else {
            throw new Exception("Cuenta no encontrada");
        }
    }

    public List<Movimiento> obtenerMovimientos() {
        return movimientoRepository.findAll();
    }
} 