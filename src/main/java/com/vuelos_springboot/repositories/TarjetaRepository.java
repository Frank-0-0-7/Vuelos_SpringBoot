package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, String> {
    // Al heredar de JpaRepository, Spring ya te regala el .save(), .findById(), .findAll(), etc.
}
