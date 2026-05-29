package com.vuelos_springboot.entities;

import lombok.Getter;

@Getter // 1. Lombok: Permite obtener las descripciones de forma limpia en el Front-End
public enum TipoTarjeta {
    DEBITO("Débito"),
    CREDITO("Crédito");

    private final String descripcion;

    // 2. Constructor interno del Enum
    TipoTarjeta(String descripcion) {
        this.descripcion = descripcion;
    }
}