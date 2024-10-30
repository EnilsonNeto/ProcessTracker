package com.promade.process.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promade.process.model.Reu;
import com.promade.process.repository.ReuRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class ReuService {
    @Autowired
    private ReuRepository reuRepository;

    public Reu salvar(Reu reu) {
        return reuRepository.save(reu);
    }

    public List<Reu> listar() {
        return reuRepository.findAll();
    }
    
    public Reu buscarPorId(Long id) {
        return reuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Réu não encontrado"));
    }
}
