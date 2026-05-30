package com.vuelos_springboot.controllers;

import com.vuelos_springboot.entities.Asiento;
import com.vuelos_springboot.entities.Vuelo;
import com.vuelos_springboot.repositories.AsientoRepository;
import com.vuelos_springboot.services.VueloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/vuelos") // Mantenemos el estándar de rutas de la cátedra
// Le pasamos la Entidad, su Servicio y el tipo de ID específico (Integer)
public class VueloController extends BaseControllerImpl<Vuelo, VueloServiceImpl, Integer> {
    // Se deja completamente vacío. La herencia ya te regala el listado automático para el Front-End.
    @GetMapping("/{idVuelo}/asientos-libres")
    public ResponseEntity<?> getAsientosLibres(@PathVariable Integer idVuelo) {
        try {
            Vuelo vuelo = servicio.findById(idVuelo);
            // Buscamos los asientos usando la consulta que creamos arriba
            List<Asiento> libres = ((AsientoRepository)asientoRepository).findAsientosLibres(vuelo.getAvion().getNumeroAvion(), idVuelo);
            return ResponseEntity.status(HttpStatus.OK).body(libres);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No se encontraron asientos.\"}");
        }
    }

    // Recordá inyectar el asientoRepository arriba en tu controlador si no lo tenías:
    @Autowired
    private AsientoRepository asientoRepository;
}