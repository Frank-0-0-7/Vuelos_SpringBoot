package com.vuelos_springboot.services;

import com.vuelos_springboot.entities.*;
import com.vuelos_springboot.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaServiceImpl {

    // Inyectamos los almacenes (Repositories) que antes manejaba su ControladoraPersistencia
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarjetaRepository tarjetaRepository;
    private final PagoRepository pagoRepository;
    private final AsientoRepository asientoRepository;

    // Constructor para que Spring Boot conecte todo automaticamente
    public ReservaServiceImpl(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository,
                              TarjetaRepository tarjetaRepository, PagoRepository pagoRepository,
                              AsientoRepository asientoRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tarjetaRepository = tarjetaRepository;
        this.pagoRepository = pagoRepository;
        this.asientoRepository = asientoRepository;
    }

    /**
     * SU MÉTODO CEREBRO: Migrado a Spring Boot conservando su misma lógica exacta.
     * Añadimos @Transactional para que si algo falla, la base de datos vuelva atrás sola (Rollback).
     */
    @Transactional(rollbackFor = Exception.class)
    public void realizarReservaCompleta(String dniStr, String nombre, String apellido,
                                        String email, String pass,
                                        String numTarjeta, String tipoTarjetaStr,
                                        String montoStr,
                                        Vuelo vuelo, Asiento asiento) throws Exception {

        // 1. VALIDACIONES INICIALES
        if (vuelo == null) throw new Exception("Debe seleccionar un Vuelo.");
        if (asiento == null) throw new Exception("Debe seleccionar un Asiento.");

        // Comprobamos disponibilidad usando Spring Data JPA
        Asiento asientoBD = asientoRepository.findById(asiento.getIdAsiento()).orElse(null);
        if (asientoBD != null && asientoBD.getReserva() != null) {
            throw new Exception("El asiento seleccionado (" + asiento.toString() + ") ya fue ocupado por otra persona.");
        }

        // 2. GESTIÓN DE USUARIO
        Usuario usuarioFinal = gestionarUsuario(dniStr, nombre, apellido, email, pass);

        // 3. GESTIÓN DE TARJETA
        Tarjeta tarjeta = gestionarTarjeta(numTarjeta, tipoTarjetaStr, usuarioFinal);

        // 4. CREAR LA RESERVA
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuarioFinal);
        reserva.setVueloReservado(vuelo);
        reserva.setAsiento(asiento);
        reserva = reservaRepository.save(reserva); // Guardamos en BD

        // 5. REGISTRAR EL PAGO
        Pago pago = new Pago();
        try {
            pago.setCantidadPago(new BigDecimal(montoStr));
        } catch (Exception e) {
            pago.setCantidadPago(BigDecimal.ZERO);
        }
        pago.setTarjeta(tarjeta);
        pago.setReserva(reserva);
        pagoRepository.save(pago); // Guardamos pago en BD

        // 6. ACTUALIZAR ESTADO DEL ASIENTO
        asiento.setReserva(reserva);
        asientoRepository.save(asiento);
    }

    // --- SUS MÉTODOS AUXILIARES TOTALMENTE RESPETADOS ---

    private Usuario gestionarUsuario(String dniStr, String nombre, String apellido, String email, String pass) throws Exception {
        int dni;
        try {
            dni = Integer.parseInt(dniStr);
        } catch (NumberFormatException e) {
            throw new Exception("El DNI debe ser numérico.");
        }

        Usuario u = usuarioRepository.findById(dni).orElse(null);

        if (u == null) {
            u = new Usuario();
            u.setDniPersona(dni);
            u.setNombrePersona(nombre);
            u.setApellidoPersona(apellido);
            u.setCorreoUsuario(email);
            u.setContraseñaUsuario(pass);
            return usuarioRepository.save(u);
        } else {
            u.setNombrePersona(nombre);
            u.setApellidoPersona(apellido);
            u.setCorreoUsuario(email);
            if (pass != null && !pass.isEmpty()) {
                u.setContraseñaUsuario(pass);
            }
            return usuarioRepository.save(u);
        }
    }

    private Tarjeta gestionarTarjeta(String numero, String tipoStr, Usuario dueño) {
        Tarjeta t = tarjetaRepository.findById(numero).orElse(null);
        if (t == null) {
            t = new Tarjeta();
            t.setNumeroTarjeta(numero);
            try {
                t.setTipoTarjeta(TipoTarjeta.valueOf(tipoStr.toUpperCase()));
            } catch (Exception e) {
                t.setTipoTarjeta(TipoTarjeta.CREDITO);
            }
            t.setUsuario(dueño);
            try {
                return tarjetaRepository.save(t);
            } catch(Exception e) {
                // Manejo de flujo simple heredado
            }
        }
        return t;
    }

    public List<Asiento> traerAsientosLibresDeVuelo(Vuelo vuelo) {
        List<Asiento> libres = new ArrayList<>();
        if (vuelo != null && vuelo.getAvion() != null) {
            List<Asiento> todos = asientoRepository.findAll();
            for (Asiento a : todos) {
                if (a.getAvion().getNumeroAvion() == vuelo.getAvion().getNumeroAvion()) {
                    if (a.getReserva() == null) { // Si no tiene reserva asignada, está libre
                        libres.add(a);
                    }
                }
            }
        }
        return libres;
    }
}