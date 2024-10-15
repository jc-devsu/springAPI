package com.devsu.cuenta_movimientos.controller;

import com.devsu.cuenta_movimientos.model.Cuenta;
import com.devsu.cuenta_movimientos.service.CuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public Mono<ResponseEntity<? extends Object>> crearCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.llamarClientePersona("/clientes", cuenta.getClienteId())
                .subscribeOn(Schedulers.boundedElastic()) 
                .flatMap(response -> {
                    if (response != null && !response.isEmpty()) {
                        
                        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
                        return Mono.just(ResponseEntity.ok(nuevaCuenta));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("ID de Cliente no encontrado.")); 
                    }
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("ID de Cliente no encontrado."))); 
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerCuentas() {
        List<Cuenta> cuentas = cuentaService.obtenerCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long id) {
        return cuentaService.obtenerCuentaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Cuenta cuentaActualizada = cuentaService.actualizarCuenta(id, cuenta);
        return ResponseEntity.ok(cuentaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
