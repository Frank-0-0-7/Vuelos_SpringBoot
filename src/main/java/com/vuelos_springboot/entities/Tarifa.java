package com.vuelos_springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal; // 1. Corrección financiera crucial

@Entity
@Table(name = "tarifas") // 2. Nombre de tabla unificado en minúscula y plural
@Getter                 // 3. Lombok: Generación automática de Getters
@Setter                 // 3. Lombok: Generación automática de Setters
@NoArgsConstructor      // 3. Lombok: Constructor vacío por defecto para JPA
@AllArgsConstructor     // 3. Lombok: Constructor con todos los atributos
@Builder                // Patrón Builder para inicialización limpia en pruebas y servicios
public class Tarifa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_tarifa") // 4. Estándar SQL snake_case aplicado
    private int numeroTarifa;

    // 1. Corrección: Cambiado double por BigDecimal para evitar pérdida de centavos
    @Column(name = "impuesto_tarifa", precision = 10, scale = 2, nullable = false)
    private BigDecimal impuestoTarifa;

    @Column(name = "precio_tarifa", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioTarifa;

    // Mapeo del ENUM para que se guarde como Texto ("BUSINESS") y no número
    @Enumerated(EnumType.STRING)
    @Column(name = "clase_tarifa", nullable = false) // 4. Estándar SQL snake_case aplicado
    private Clase claseTarifa;

    // RELACIÓN MANY-TO-ONE (Bidireccional con Vuelo)
    @ManyToOne(fetch = FetchType.LAZY) // 5. Optimización de carga perezosa
    @JoinColumn(name = "numero_vuelo", nullable = false) // 4. Estándar SQL snake_case aplicado
    @JsonIgnore
    private Vuelo vuelo;
}
