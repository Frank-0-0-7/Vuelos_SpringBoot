package com.vuelos_springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aviones") // 1. Nombre de tabla unificado en minúscula y plural
@Getter                 // 2. Lombok: Generación automática de Getters
@Setter                 // 2. Lombok: Generación automática de Setters
@NoArgsConstructor      // 2. Lombok: Constructor vacío por defecto para JPA
@AllArgsConstructor     // 2. Lombok: Constructor con todos los atributos
@Builder                // Patrón Builder para inicialización limpia en pruebas y servicios
public class Avion implements Serializable {

    @Id
    @Column(name = "numero_avion", nullable = false)
    // 3. Estándar SQL snake_case. Sin @GeneratedValue por IDs manuales explícitos.
    private int numeroAvion;

    @Column(name = "tipo_turbina", length = 50) // 3. Estándar SQL snake_case aplicado
    private String tipoTurbina;

    @Column(name = "tipo_avion", length = 50) // 3. Estándar SQL snake_case aplicado
    private String tipoAvion;

    // RELACIÓN 1:N CON ASIENTOS
    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default // 4. Requerido por Lombok Builder para inicializar la lista correctamente
    private List<Asiento> asientos = new ArrayList<>();

    // RELACIÓN 1:N CON VUELOS
    @OneToMany(mappedBy = "avion", fetch = FetchType.LAZY) // 5. Carga perezosa explícita
    @Builder.Default
    private List<Vuelo> vuelos = new ArrayList<>();

    // --- MÉTODOS DE LÓGICA DE NEGOCIO ORIGINALES RESPETADOS ---

    // Método helper para agregar asientos vinculando la relación automáticamente
    public void addAsiento(Asiento asiento) {
        this.asientos.add(asiento);
        asiento.setAvion(this);
    }

    @Override
    public String toString() {
        return "Avion #" + numeroAvion + " (" + tipoAvion + ")";
    }
}