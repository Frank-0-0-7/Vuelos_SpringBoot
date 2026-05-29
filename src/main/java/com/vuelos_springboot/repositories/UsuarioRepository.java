package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Al heredar de JpaRepository, Spring ya te regala el .save(), .findById(), .findAll(), etc.
}