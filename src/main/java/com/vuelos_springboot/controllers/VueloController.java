package com.vuelos_springboot.controllers;

import com.vuelos_springboot.entities.Vuelo;
import com.vuelos_springboot.services.VueloServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/vuelos") // Mantenemos el estándar de rutas de la cátedra
// Le pasamos la Entidad, su Servicio y el tipo de ID específico (Integer)
public class VueloController extends BaseControllerImpl<Vuelo, VueloServiceImpl, Integer> {
    // Se deja completamente vacío. La herencia ya te regala el listado automático para el Front-End.
}