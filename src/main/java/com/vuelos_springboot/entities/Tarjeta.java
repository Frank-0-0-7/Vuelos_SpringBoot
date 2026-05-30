package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "tarjetas") // 1. Nombre de tabla unificado en minúscula y plural
@Getter                  // 2. Lombok: Generación automática de Getters
@Setter                  // 2. Lombok: Generación automática de Setters
@NoArgsConstructor        // 2. Lombok: Constructor vacío obligatorio para JPA
@AllArgsConstructor       // 2. Lombok: Constructor con todos los atributos
@Builder                  // Patrón Builder para inicialización limpia en pruebas y servicios
public class Tarjeta implements Base<String> {

    // PK es String (VARCHAR 16) - Sin @GeneratedValue porque se ingresa manualmente
    @Id
    @Column(name = "numero_tarjeta", length = 16, nullable = false) // 3. Estándar SQL snake_case aplicado
    private String numeroTarjeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tarjeta", nullable = false) // 3. Estándar SQL snake_case aplicado
    private TipoTarjeta tipoTarjeta;

    // RELACIÓN N:1 (Muchas tarjetas pertenecen a un Usuario)
    @ManyToOne(fetch = FetchType.LAZY) // 4. Optimización de carga perezosa explícita
    @JoinColumn(name = "numero_usuario", nullable = false) // 3. Estándar SQL snake_case aplicado
    private Usuario usuario;


    @Override
    public String getId() {
        return this.numeroTarjeta;
    }
}