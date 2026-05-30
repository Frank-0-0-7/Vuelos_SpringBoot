package com.vuelos_springboot.services;

import com.vuelos_springboot.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.util.List;

// Ajuste clave: Cambiamos <E extends Base> por <E extends Base<ID>>
public interface BaseService<E extends Base<ID>, ID extends Serializable> {
    public List<E> findAll() throws Exception;
    public Page<E> findAll(Pageable pageable) throws Exception;
    public E findById(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(ID id, E entity) throws Exception;
    public boolean delete(ID id) throws Exception;
}
