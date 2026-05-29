package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "asientos") // 1. Nombre de tabla unificado en minúscula y plural
@Getter                   // 2. Lombok: Generación automática de Getters
@Setter                   // 2. Lombok: Generación automática de Setters
@NoArgsConstructor        // 2. Lombok: Constructor vacío por defecto para JPA
@AllArgsConstructor       // 2. Lombok: Constructor con todos los atributos
@Builder                  // Patrón Builder para inicialización limpia en testing
public class Asiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento") // 3. Estándar SQL snake_case aplicado
    private int idAsiento;

    @Column(name = "fila_asiento") // 3. Estándar SQL snake_case aplicado
    private int filaAsiento;

    @Column(name = "letra_asiento") // 3. Estándar SQL snake_case aplicado
    private char letraAsiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "clase_asiento") // 3. Estándar SQL snake_case aplicado
    private Clase claseAsiento;

    // --- RELACIÓN N:1 CON AVION (OBLIGATORIA) ---
    @ManyToOne(fetch = FetchType.LAZY) // 4. Optimización de carga perezosa
    @JoinColumn(name = "numero_avion", nullable = false) // 3. Estándar SQL snake_case aplicado
    private Avion avion;

    // --- RELACIÓN 1:1 CON RESERVA (Inversa) ---
    @OneToOne(mappedBy = "asiento", fetch = FetchType.LAZY) // 4. Optimización de carga perezosa
    private Reserva reserva;

    // 5. Método toString personalizado original respetado tal cual
    @Override
    public String toString() {
        // "1A (TURISTA)"
        return filaAsiento + "" + letraAsiento + " (" + claseAsiento + ")";
    }
}
