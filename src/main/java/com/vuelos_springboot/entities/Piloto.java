package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pilotos")
@PrimaryKeyJoinColumn(name = "dni_persona")
@Getter
@Setter
@NoArgsConstructor
public class Piloto extends Persona implements Serializable {

    // Columna autoincremental de la BD (Solo lectura para JPA)
    @Column(name = "numero_piloto", insertable = false, updatable = false) // 2. Convención snake_case aplicada
    private int numeroPiloto;

    // RELACIÓN 1:N (Un piloto -> Muchos vuelos)
    @OneToMany(mappedBy = "piloto", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 4. Carga perezosa explícita
    private List<Vuelo> vuelos = new ArrayList<>();

    // --- CONSTRUCTORES MANUALES PARA HERENCIA ---
    // Nota: Conservamos el constructor explícito debido a la llamada super() requerida por la herencia.
    public Piloto(int numeroPiloto, int dniPersona, String nombrePersona, String apellidoPersona) {
        super(dniPersona, nombrePersona, apellidoPersona);
        this.numeroPiloto = numeroPiloto;
    }

    // Metodo helper para agregar vuelos y mantener la coherencia
    public void addVuelo(Vuelo vuelo) {
        this.vuelos.add(vuelo);
        vuelo.setPiloto(this);
    }
}