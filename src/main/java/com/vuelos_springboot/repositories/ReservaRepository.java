package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    // Al heredar de JpaRepository, Spring ya te regala el .save(), .findById(), .findAll(), etc.
}