package com.vuelos_springboot.repositories;

import com.vuelos_springboot.entities.Asiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientoRepository extends BaseRepository<Asiento, Integer> {

    // Trae solo los asientos de un avión que NO estén ocupados en un vuelo específico
    @Query(value = "SELECT a.* FROM asientos a " +
            "WHERE a.numero_avion = :numeroAvion " +
            "AND a.id_asiento NOT IN (" +
            "   SELECT r.id_asiento FROM reservas r WHERE r.numero_vuelo = :numeroVuelo" +
            ")", nativeQuery = true)
    List<Asiento> findAsientosLibres(@Param("numeroAvion") int numeroAvion, @Param("numeroVuelo") int numeroVuelo);
}