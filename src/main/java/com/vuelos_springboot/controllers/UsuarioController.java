package com.vuelos_springboot.controllers;

import com.vuelos_springboot.entities.Usuario;
import com.vuelos_springboot.services.UsuarioService;
import com.vuelos_springboot.services.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl, Integer> {
    // Al heredar de BaseControllerImpl, ya ganás el ABM automático de usuarios para el Front-End
}