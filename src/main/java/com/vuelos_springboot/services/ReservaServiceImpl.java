package com.vuelos_springboot.services;

import com.vuelos_springboot.dtos.ReservaDTO;
import com.vuelos_springboot.entities.*;
import com.vuelos_springboot.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl extends BaseServiceImpl<Reserva, Integer> implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarjetaRepository tarjetaRepository;
    private final PagoRepository pagoRepository;
    private final AsientoRepository asientoRepository;
    private final VueloRepository vueloRepository; // Inyectamos Vuelo para buscar por ID

    // El constructor conecta todos los repositorios necesarios
    public ReservaServiceImpl(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository,
                              TarjetaRepository tarjetaRepository, PagoRepository pagoRepository,
                              AsientoRepository asientoRepository, VueloRepository vueloRepository) {
        super(reservaRepository);
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tarjetaRepository = tarjetaRepository;
        this.pagoRepository = pagoRepository;
        this.asientoRepository = asientoRepository;
        this.vueloRepository = vueloRepository;
    }

    /**
     * SU MÉTODO CEREBRO OPTIMIZADO: Ahora coincide exactamente con la firma de la interfaz.
     * Recibe idVuelo e idAsiento como enteros para trabajar directo desde el controlador.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void realizarReservaCompletaConIds(String dniStr, String nombre, String apellido,
                                              String email, String pass,
                                              String numTarjeta, String tipoTarjetaStr,
                                              String montoStr,
                                              int idVuelo, int idAsiento) throws Exception {

        // 1. VALIDACIONES INICIALES Y BÚSQUEDA EN BD
        Vuelo vuelo = vueloRepository.findById(idVuelo).orElse(null);
        if (vuelo == null) throw new Exception("Debe seleccionar un Vuelo válido.");

        Asiento asiento = asientoRepository.findById(idAsiento).orElse(null);
        if (asiento == null) throw new Exception("Debe seleccionar un Asiento válido.");

        // Comprobamos disponibilidad real en el motor
        if (asiento.getReserva() != null) {
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

    /**
     * MÉTODO DE CONVERSIÓN DTO: Lee tus entidades físicas y arma el listado plano
     * que reemplaza al viejo TableModel de Swing para tu tabla del Front-End.
     */
    @Override
    public List<ReservaDTO> listarReservasParaTablaWeb(List<Reserva> listaReservas) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return listaReservas.stream().map(r -> {
            Usuario u = r.getUsuario();
            Vuelo v = r.getVueloReservado();
            Pago p = r.getPago();
            Asiento a = r.getAsiento();

            String nombreDestino = "N/A";
            String ciudadDestino = "N/A";
            String nombreAerolinea = "N/A";
            String numAvion = "N/A";
            String turbina = "N/A";
            String fecha = "N/A";

            if (v != null) {
                if (v.getAeropuertoDestino() != null) {
                    nombreDestino = v.getAeropuertoDestino().getNombreAeropuerto();
                    if (v.getAeropuertoDestino().getCiudad() != null) {
                        ciudadDestino = v.getAeropuertoDestino().getCiudad().getNombreCiudad();
                    }
                }
                if (v.getAerolinea() != null) {
                    nombreAerolinea = v.getAerolinea().getNombreAerolinea();
                }
                if (v.getAvion() != null) {
                    numAvion = String.valueOf(v.getAvion().getNumeroAvion());
                    turbina = v.getAvion().getTipoTurbina();
                }
                if (v.getFechaSalida() != null) {
                    fecha = v.getFechaSalida().format(formatter);
                }
            }

            String numTarjeta = "-";
            String tipoTarjeta = "-";

            if (p != null && p.getTarjeta() != null) {
                numTarjeta = p.getTarjeta().getNumeroTarjeta();
                if (p.getTarjeta().getTipoTarjeta() != null) {
                    tipoTarjeta = p.getTarjeta().getTipoTarjeta().toString();
                }
            }

            return new ReservaDTO(
                    (u != null) ? u.getDniPersona() : 0,
                    (u != null) ? u.getNombrePersona() : "-",
                    (u != null) ? u.getApellidoPersona() : "-",
                    r.getNumeroReserva(),
                    (v != null) ? v.getNumeroVuelo() : 0,
                    (p != null) ? p.getCantidadPago() : BigDecimal.ZERO,
                    nombreDestino,
                    ciudadDestino,
                    (a != null) ? String.valueOf(a.getFilaAsiento()) : "Sin Asignar",
                    (a != null) ? String.valueOf(a.getLetraAsiento()) : "-",
                    numAvion,
                    turbina,
                    fecha,
                    nombreAerolinea,
                    (p != null) ? String.valueOf(p.getNumeroPago()) : "-",
                    numTarjeta,
                    tipoTarjeta
            );
        }).collect(Collectors.toList());
    }

    // --- SUS MÉTODOS AUXILIARES PRIVADOS TOTALMENTE RESPETADOS ---

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
                    if (a.getReserva() == null) {
                        libres.add(a);
                    }
                }
            }
        }
        return libres;
    }
}