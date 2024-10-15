package com.devsu.cuenta_movimientos.controller;

import com.devsu.cuenta_movimientos.model.Cuenta;
import com.devsu.cuenta_movimientos.model.Movimiento;
import com.devsu.cuenta_movimientos.service.CuentaService;
import com.devsu.cuenta_movimientos.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimiento>> getAllMovimientos() {
        return ResponseEntity.ok(movimientoService.obtenerMovimientos());
    }

    @PostMapping
    public ResponseEntity<?> createMovimiento(@RequestBody Movimiento movimiento) {
        try {
            return ResponseEntity.ok(movimientoService.registrarMovimiento(movimiento));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
}



