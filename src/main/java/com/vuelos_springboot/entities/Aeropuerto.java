package com.vuelos_springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "aeropuertos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aeropuerto implements Serializable {

    @Id
    @Column(name = "nombre_aeropuerto", length = 45, nullable = false)
    // 3. Estandarización SQL a snake_case y restricción PK explícita
    private String nombreAeropuerto;

    // RELACIÓN N:1 (Muchos aeropuertos están en una ciudad)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 4. Configuración explícita de carga perezosa
    @JoinColumn(name = "nombre_ciudad", nullable = false) // 3. Estándar snake_case aplicado
    private Ciudad ciudad;

    // 5. Metodo toString personalizado original respetado tal cual
    @Override
    public String toString() {
        return nombreAeropuerto;
    }
}