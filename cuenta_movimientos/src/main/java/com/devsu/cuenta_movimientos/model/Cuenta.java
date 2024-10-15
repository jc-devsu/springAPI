package com.devsu.cuenta_movimientos.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroCuenta;

    @Column(nullable = false)
    private String tipoCuenta;

    @Column(nullable = false)
    private double saldoInicial;

    @Column(nullable = false)
    private boolean estado;

    @Column(nullable = false)
    private Long clienteId; // Referencia al cliente asociado

    @Column(nullable = false)
    private String nombreCliente;

    @PrePersist
    private void asignarNumeroCuenta() {
        if (this.numeroCuenta == null || this.numeroCuenta.isEmpty()) {
            this.numeroCuenta = generarNumeroCuentaUnico();
        }
    }

    private String generarNumeroCuentaUnico() {
        return "CU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta() {
        asignarNumeroCuenta();
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}