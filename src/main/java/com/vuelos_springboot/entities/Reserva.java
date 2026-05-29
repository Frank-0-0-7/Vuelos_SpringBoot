package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "reservas") // 1. Nombre de tabla unificado en minúscula y plural
@Getter                   // 2. Lombok: Generación automática de Getters
@Setter                   // 2. Lombok: Generación automática de Setters
@NoArgsConstructor        // 2. Lombok: Constructor vacío por defecto para JPA
@AllArgsConstructor       // 2. Lombok: Constructor con todos los atributos
@Builder                  // Patrón Builder para inicialización limpia en pruebas y servicios
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_reserva") // 3. Estándar SQL snake_case aplicado
    private int numeroReserva;

    // --- RELACIÓN CON USUARIO (N:1) ---
    @ManyToOne(fetch = FetchType.LAZY) // 4. Optimización de carga perezosa
    @JoinColumn(name = "numero_usuario", nullable = false) // 3. Estándar SQL snake_case aplicado
    private Usuario usuario;

    // --- RELACIÓN CON VUELO ---
    @ManyToOne(fetch = FetchType.LAZY) // 4. Optimización de carga perezosa
    @JoinColumn(name = "numero_vuelo", nullable = false) // 3. Estándar SQL snake_case aplicado
    private Vuelo vueloReservado;

    // --- RELACIÓN CON ASIENTO ---
    @OneToOne(fetch = FetchType.LAZY) // 4. Optimización de carga perezosa
    @JoinColumn(name = "id_asiento") // 3. Estándar SQL snake_case aplicado
    private Asiento asiento;

    // --- RELACIÓN CON PAGO (Inversa) ---
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 4. Carga perezosa
    private Pago pago;

    // 5. Modificamos el Setter generado por Lombok para mantener su lógica de negocio original
    public void setPago(Pago pago) {
        this.pago = pago;
        if (pago != null) {
            pago.setReserva(this);
        }
    }
}
