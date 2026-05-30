package com.vuelos_springboot.entities;

import java.io.Serializable;

// Al poner <ID>, la interfaz se adapta al tipo de dato de la PK de cada entidad
public interface Base<ID extends Serializable> extends Serializable {
    ID getId(); // Método obligatorio que deberán tener tus entidades
}