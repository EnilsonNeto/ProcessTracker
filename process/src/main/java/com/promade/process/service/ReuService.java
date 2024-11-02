package com.promade.process.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promade.process.dto.ReuDTO;
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

    public Reu atualizar(Long id, ReuDTO reuDTO) {
        Reu reu = buscarPorId(id);
        reu.setNome(reuDTO.getNome());
        reu.setCpf(reuDTO.getCpf());
        reu.setTelefone(reuDTO.getTelefone());
        return reuRepository.save(reu);
    }

    public void excluir(Long id) {
        Reu reu = buscarPorId(id);
        reuRepository.delete(reu);
    }
}
