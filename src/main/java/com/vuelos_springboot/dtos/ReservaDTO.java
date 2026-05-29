package com.vuelos_springboot.dtos;

import java.math.BigDecimal;

/**
 * Un Record en Java 21 es una clase inmutable que genera automáticamente
 * los atributos, constructor completo, getters y toString en una sola línea.
 * Ideal para enviar datos limpios al Front-End sin sobre-ingeniería.
 */
public record ReservaDTO(
        int dni,
        String nombre,
        String apellido,
        int numeroReserva,
        int numeroVuelo,
        BigDecimal montoTotal,
        String aeropuertoDestino,
        String ciudadDestino,
        String fila,
        String letra,
        String numeroAvion,
        String turbina,
        String fechaSalida,
        String aerolinea,
        String numeroPago,
        String numeroTarjeta,
        String tipoTarjeta
) {}