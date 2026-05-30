package com.vuelos_springboot.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vuelos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vuelo implements Base<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_vuelo") // 3. Estándar snake_case para columnas SQL
    private int numeroVuelo;

    // Relación N:1 (Muchos vuelos, un avion)
    @ManyToOne(fetch = FetchType.LAZY) // 4. Optimización de carga perezosa
    @JoinColumn(name = "numero_avion") // 3. Estándar snake_case aplicado
    private Avion avion;

    // Relaciones para Origen y Destino
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aeropuerto_origen")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aeropuerto_destino")
    private Aeropuerto aeropuertoDestino;

    // RELACIÓN ONE-TO-MANY (Un vuelo -> Muchas tarifas)
    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default // 5. Lombok Builder Default para inicializar la lista correctamente
    private List<Tarifa> tarifas = new ArrayList<>();

    // Fechas usando LocalDateTime
    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @Column(name = "fecha_llegada")
    private LocalDateTime fechaLlegada;

    // Relación N:1 (Muchos vuelos, un piloto)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_piloto")
    private Piloto piloto;

    // Relación N:1 (Muchos vuelos, una aerolinea)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nombre_aerolinea")
    private Aerolinea aerolinea;

    // Metodo helper personalizado respetado tal cual
    public void addTarifa(Tarifa tarifa) {
        this.tarifas.add(tarifa);
        tarifa.setVuelo(this);
    }

    @Override
    public String toString() {
        String origenStr = (aeropuertoOrigen != null) ? aeropuertoOrigen.getNombreAeropuerto() : "???";
        String destinoStr = (aeropuertoDestino != null) ? aeropuertoDestino.getNombreAeropuerto() : "???";

        return "Vuelo #" + this.numeroVuelo + " [" + origenStr + " -> " + destinoStr + "]";
    }

    @Override
    public Integer getId() {
        return this.numeroVuelo; // Retornar su PK
    }
}