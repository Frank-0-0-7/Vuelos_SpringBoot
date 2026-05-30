package com.vuelos_springboot.services;

import com.vuelos_springboot.entities.Vuelo;

public interface VueloService extends BaseService<Vuelo, Integer> {
    // Se queda vacío porque solo necesitas el findAll() que ya viene por herencia genérica
}