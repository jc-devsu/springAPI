package com.devsu.cliente_persona.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    // Getters and Setters
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}