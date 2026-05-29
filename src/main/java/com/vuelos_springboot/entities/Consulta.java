package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_consulta") // 3. Estándar SQL snake_case aplicado
    private int numeroConsulta;

    @ManyToOne(fetch = FetchType.LAZY) // 4. Optimización de carga perezosa
    @JoinColumn(name = "numero_usuario") // 3. Estándar SQL snake_case aplicado
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY) // 4. Optimización de carga perezosa
    @JoinColumn(name = "numero_vuelo") // 3. Estándar SQL snake_case aplicado
    private Vuelo vuelo;
}
