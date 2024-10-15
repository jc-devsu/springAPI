package com.devsu.cuenta_movimientos.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String tipoMovimiento;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private double saldo;

    @Column(nullable = false)
    private boolean estado = false;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fechaActual) {
        this.fecha = fechaActual;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor>0){
            this.tipoMovimiento = "Deposito";
        } else if (valor<0) {
            this.tipoMovimiento = "Retiro";
        } {
        }
        
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
