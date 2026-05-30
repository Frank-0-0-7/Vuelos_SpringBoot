package com.vuelos_springboot.controllers;

import com.vuelos_springboot.dtos.ReservaDTO;
import com.vuelos_springboot.entities.Reserva;
import com.vuelos_springboot.services.ReservaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/reservas")
public class ReservaController extends BaseControllerImpl<Reserva, ReservaServiceImpl, Integer> {

    /**
     * POST: http://localhost:8080/api/v1/reservas/realizar-completa
     * Recibe los datos sueltos del formulario en un JSON plano desde el Front-End
     */
    @PostMapping("/realizar-completa")
    public ResponseEntity<?> realizarReservaCompleta(@RequestBody Map<String, Object> payload) {
        try {
            // Desmenuzamos el JSON recibido tal cual lo procesaba tu vieja lógica
            String dniStr = String.valueOf(payload.get("dniStr"));
            String nombre = String.valueOf(payload.get("nombre"));
            String apellido = String.valueOf(payload.get("apellido"));
            String email = String.valueOf(payload.get("email"));
            String pass = String.valueOf(payload.get("pass"));
            String numTarjeta = String.valueOf(payload.get("numTarjeta"));
            String tipoTarjetaStr = String.valueOf(payload.get("tipoTarjetaStr"));
            String montoStr = String.valueOf(payload.get("montoStr"));

            int idVuelo = (Integer) payload.get("idVuelo");
            int idAsiento = (Integer) payload.get("idAsiento");

            // Invocamos tu lógica de transacciones inyectada por herencia (servicio)
            servicio.realizarReservaCompletaConIds(dniStr, nombre, apellido, email, pass,
                    numTarjeta, tipoTarjetaStr, montoStr,
                    idVuelo, idAsiento);

            // Retornamos éxito con formato JSON estructurado como exige la cátedra
            return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\":\"¡Reserva e impacto financiero realizados con éxito!\"}");

        } catch (Exception e) {
            // Captura cualquier error de validación (ej: Asiento Ocupado) y se lo informa al Front-End
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    /**
     * GET: http://localhost:8080/api/v1/reservas/tabla-web
     * Alimenta de forma directa la grilla de tu Front-End usando el ReservaDTO plano
     */
    @GetMapping("/tabla-web")
    public ResponseEntity<?> obtenerReservasParaTabla() {
        try {
            // Buscamos todas las entidades físicas usando el método heredado del profesor
            List<Reserva> reservasBD = servicio.findAll();

            // Las transformamos en registros DTO planos usando tu formateador
            List<ReservaDTO> dtosPlanos = servicio.listarReservasParaTablaWeb(reservasBD);

            return ResponseEntity.status(HttpStatus.OK).body(dtosPlanos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}