package com.vuelos_springboot.services;

import com.vuelos_springboot.dtos.ReservaDTO;
import com.vuelos_springboot.entities.Reserva;
import java.util.List;

// Hereda los métodos CRUD automáticos adaptados a tu ID elástico Integer
public interface ReservaService extends BaseService<Reserva, Integer> {

    // Declaramos tu método "Cerebro" personalizado
    void realizarReservaCompletaConIds(String dniStr, String nombre, String apellido,
                                       String email, String pass,
                                       String numTarjeta, String tipoTarjetaStr,
                                       String montoStr,
                                       int idVuelo, int idAsiento) throws Exception;

    // Declaramos el método que alimenta tu grilla web con el DTO plano
    List<ReservaDTO> listarReservasParaTablaWeb(List<Reserva> listaReservas);
}