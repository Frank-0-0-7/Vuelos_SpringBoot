package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Persona implements Serializable {

    @Id
    @Column(name = "dni_persona", nullable = false)
    // 4. Estándar SQL snake_case aplicado. Sin @GeneratedValue porque es un DNI real manual.
    private int dniPersona;

    @Column(name = "nombre_persona", length = 100, nullable = false) // 4. Estándar SQL snake_case y restricciones explícitas
    private String nombrePersona;

    @Column(name = "apellido_persona", length = 100, nullable = false) // 4. Estándar SQL snake_case y restricciones explícitas
    private String apellidoPersona;

    // 5. Metodo toString personalizado original respetado tal cual para la capa de presentación
    @Override
    public String toString() {
        return nombrePersona + " " + apellidoPersona + " (DNI: " + dniPersona + ")";
    }
}
