package com.devsu.cuenta_movimientos.service;

import com.devsu.cuenta_movimientos.model.Cuenta;
import com.devsu.cuenta_movimientos.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CuentaService {

    private WebClient webClient;


    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public Cuenta crearCuenta(Cuenta cuenta) {
    
        return cuentaRepository.save(cuenta);
    }

    public Mono<String> llamarClientePersona(String endpoint, Long data) {
        this.webClient = WebClient.create("http://localhost:8081");

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpoint).path("/{id}").build(data))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        // Devuelve un Mono vacío si el cliente no fue encontrado (404)
                        return Mono.empty();
                    }
                    // En caso de otros errores 4xx, se puede devolver una excepción controlada o vacío
                    return Mono.error(new RuntimeException("Error al buscar el cliente"));
                })
                .bodyToMono(String.class)
                .switchIfEmpty(Mono.empty());  // Si el cliente no es encontrado, regresa Mono vacío
    }

    


    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id);
    }

    @Transactional
    public Cuenta actualizarCuenta(Long id, Cuenta detallesCuenta) {
        Cuenta cuentaExistente = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuentaExistente.setTipoCuenta(detallesCuenta.getTipoCuenta());
        cuentaExistente.setSaldoInicial(detallesCuenta.getSaldoInicial());
        cuentaExistente.setEstado(detallesCuenta.getEstado());
        // Actualiza otros campos según sea necesario

        return cuentaRepository.save(cuentaExistente);
    }

    @Transactional
    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}
