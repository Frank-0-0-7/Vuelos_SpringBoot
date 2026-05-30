package com.vuelos_springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios") // 1. Nombre de tabla unificado en minúscula y plural
@PrimaryKeyJoinColumn(name = "dni_persona") // 2. Mapeo de herencia relacional en snake_case
@Getter                  // 3. Lombok: Generación automática de Getters
@Setter                  // 3. Lombok: Generación automática de Setters
@NoArgsConstructor       // 3. Lombok: Constructor vacío obligatorio para JPA
public class Usuario extends Persona implements Serializable {

    // Columna autoincremental de la BD (Solo lectura para JPA)
    @Column(name = "numero_usuario", insertable = false, updatable = false) // 2. Convención snake_case aplicada
    private Integer numeroUsuario;

    @Column(name = "contrasenia_usuario", nullable = false) // 4. Corrección de caracteres especiales (ñ)
    private String contraseñaUsuario;

    @Column(name = "correo_electronico_usuario", nullable = false, unique = true) // 2. Convención snake_case aplicada
    private String correoUsuario;

    // --- RELACIÓN 1:N CON RESERVAS ---
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 5. Carga perezosa explícita
    private List<Reserva> reservas = new ArrayList<>();

    // RELACIÓN 1:N CON TARJETAS
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tarjeta> tarjetas = new ArrayList<>();

    // RELACIÓN 1:N CON CONSULTAS
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consulta> consultas = new ArrayList<>();

    // --- CONSTRUCTOR MANUAL PARA HERENCIA ---
    // Conservamos su constructor explícito debido a la llamada super() requerida por la herencia relacional
    public Usuario(int dniPersona, String nombrePersona, String apellidoPersona,
                   String contraseñaUsuario, String correoUsuario) {
        super(dniPersona, nombrePersona, apellidoPersona);
        this.contraseñaUsuario = contraseñaUsuario;
        this.correoUsuario = correoUsuario;
    }

    // --- MÉTODOS HELPER ORIGINALES CONSERVADOS INTACTOS ---

    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
        reserva.setUsuario(this);
    }

    public void addTarjeta(Tarjeta tarjeta) {
        this.tarjetas.add(tarjeta);
        tarjeta.setUsuario(this);
    }

    public void addConsulta(Consulta consulta) {
        this.consultas.add(consulta);
        consulta.setUsuario(this);
    }
}
