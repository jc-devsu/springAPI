package com.devsu.cuenta_movimientos.service;

import com.devsu.cuenta_movimientos.model.Cuenta;
import com.devsu.cuenta_movimientos.model.Movimiento;
import com.devsu.cuenta_movimientos.repository.CuentaRepository;
import com.devsu.cuenta_movimientos.repository.MovimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Map<String, Object> generarReporteEstadoCuenta(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        if (cuentas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron cuentas para el cliente especificado");
        }

        List<Movimiento> movimientos = movimientoRepository.findByCuentaClienteIdAndFechaBetween(clienteId, fechaInicio, fechaFin);

        Map<String, Object> reporte = new HashMap<>();
        reporte.put("cuentas", cuentas);
        reporte.put("movimientos", movimientos);

        return reporte;
    }
}

