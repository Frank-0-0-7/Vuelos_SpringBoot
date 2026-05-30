package com.vuelos_springboot.services;

import com.vuelos_springboot.entities.Usuario;

public interface UsuarioService extends BaseService<Usuario, Integer> {
    // Se deja vacío, hereda todo el CRUD automático del profesor
}