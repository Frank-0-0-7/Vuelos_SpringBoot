package com.vuelos_springboot.services;

import com.vuelos_springboot.entities.Base;          // 1. IMPORTACIÓN CORREGIDA
import com.vuelos_springboot.repositories.BaseRepository; // 1. IMPORTACIÓN CORREGIDA
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// 2. Acoplamos la clase abstracta con tu nueva interfaz Base elástica
public abstract class BaseServiceImpl<E extends Base<ID>, ID extends Serializable> implements BaseService<E, ID> {

    // 3. Le avisamos al repositorio que use el ID genérico correspondiente
    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findAll(pageable);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(entity);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception { // Corregido 'ID ID' por 'ID id' por prolijidad
        try {
            if(baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception("No se encontro el registro");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}