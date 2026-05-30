package com.vuelos_springboot.services;

import com.vuelos_springboot.entities.Usuario;
import com.vuelos_springboot.repositories.BaseRepository;
import com.vuelos_springboot.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Integer> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CORRECCIÓN: Pedimos UsuarioRepository en el parámetro en lugar de la interfaz genérica
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository); // Se lo pasamos al padre, que lo recibe feliz como BaseRepository
        this.usuarioRepository = usuarioRepository;
    }
}