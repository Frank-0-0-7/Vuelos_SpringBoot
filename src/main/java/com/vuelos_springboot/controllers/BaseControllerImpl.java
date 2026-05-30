package com.vuelos_springboot.controllers;

import com.vuelos_springboot.entities.Base;
import com.vuelos_springboot.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;

// Reemplazamos el tipo Long rígido del profesor por el genérico <ID>
public abstract class BaseControllerImpl<E extends Base<ID>, S extends BaseServiceImpl<E, ID>, ID extends Serializable> implements BaseController<E, ID> {

    @Autowired
    protected S servicio;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, porfavor intente mas tarde.\"}");
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, porfavor intente mas tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable ID id){ // ID elástico
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, porfavor intente mas tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody E entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, porfavor intente mas tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity){ // ID elástico
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, entity));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, porfavor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ID id){ // ID elástico
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, porfavor intente mas tarde.\"}");
        }
    }
}