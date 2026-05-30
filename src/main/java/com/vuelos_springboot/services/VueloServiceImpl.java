package com.vuelos_springboot.services;

import com.vuelos_springboot.entities.Vuelo;
import com.vuelos_springboot.repositories.BaseRepository;
import com.vuelos_springboot.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VueloServiceImpl extends BaseServiceImpl<Vuelo, Integer> implements VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    // El constructor le pasa el repositorio al padre genérico (super) tal como pide el profesor
    public VueloServiceImpl(BaseRepository<Vuelo, Integer> baseRepository) {
        super(baseRepository);
    }
}