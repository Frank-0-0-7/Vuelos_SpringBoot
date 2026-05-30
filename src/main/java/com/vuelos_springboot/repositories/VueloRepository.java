package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Vuelo;
import org.springframework.stereotype.Repository;

@Repository
// Al heredar de tu BaseRepository elástico, ganas todos los métodos CRUD automáticos
public interface VueloRepository extends BaseRepository<Vuelo, Integer> {
}