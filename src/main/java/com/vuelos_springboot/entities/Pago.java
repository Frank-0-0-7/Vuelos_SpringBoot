package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "pagos") // 1. Nombre de tabla unificado en minúscula y plural
@Getter                // 2. Lombok: Generación automática de Getters
@Setter                // 2. Lombok: Generación automática de Setters
@NoArgsConstructor     // 2. Lombok: Constructor vacío por defecto para JPA
@AllArgsConstructor    // 2. Lombok: Constructor con todos los atributos
@Builder               // Patrón Builder para inicialización limpia en pruebas y servicios
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pago") // 3. Estándar SQL snake_case aplicado
    private int numeroPago;

    // BigDecimal mapea perfecto a DECIMAL(10,2) en SQL
    @Column(name = "cantidad_pago", precision = 10, scale = 2) // 4. Definición explícita de precisión decimal
    private BigDecimal cantidadPago;

    // --- RELACIÓN 1:1 CON RESERVA ---
    // Esta clase es la "Dueña" porque tiene la columna FK (JoinColumn).
    @OneToOne(fetch = FetchType.LAZY) // 5. Optimización de carga perezosa
    @JoinColumn(name = "numero_reserva", unique = true, nullable = false) // 3. Estándar SQL snake_case aplicado
    private Reserva reserva;

    // --- RELACIÓN CON TARJETA ---
    @ManyToOne(fetch = FetchType.LAZY) // 5. Optimización de carga perezosa
    @JoinColumn(name = "numero_tarjeta", nullable = false) // 3. Estándar SQL snake_case aplicado
    private Tarjeta tarjeta;
}
