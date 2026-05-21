package com.vuelos_springboot.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aerolineas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aerolinea implements Serializable {

    @Id
    @Column(name = "nombre_aerolinea", length = 45, nullable = false)
    private String nombreAerolinea;

    // RELACIÓN 1:N (Una aerolinea tiene muchos vuelos)
    @OneToMany(mappedBy = "aerolinea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vuelo> vuelos = new ArrayList<>();

    @Override
    public String toString() {
        return nombreAerolinea;
    }
}