package com.vuelos_springboot.entities;

import lombok.Getter;

@Getter // 1. Lombok: Permite obtener el nombre o valores del enum de forma limpia
public enum Clase {
    BUSINESS("Ejecutivo"),
    TURISTA("Turista"),
    ECONOMY("Economico");

    private final String descripcion;

    // 2. Constructor interno del Enum
    Clase(String descripcion) {
        this.descripcion = descripcion;
    }
}
