package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ciudades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ciudad implements Serializable {

    @Id
    @Column(name = "nombre_ciudad", length = 45, nullable = false)
    // 3. Estandarización SQL a snake_case y restricción PK explícita
    private String nombreCiudad;

    // RELACIÓN 1:N (Una ciudad tiene muchos aeropuertos)
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // 4. Optimización de carga perezosa y 5. Builder Default de Lombok
    @Builder.Default
    private List<Aeropuerto> aeropuertos = new ArrayList<>();

    // 6. Metodo toString personalizado original respetado tal cual
    @Override
    public String toString() {
        return nombreCiudad;
    }
}