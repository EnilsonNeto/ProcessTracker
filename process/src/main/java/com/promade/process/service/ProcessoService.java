package com.promade.process.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promade.process.model.Processo;
import com.promade.process.model.Reu;
import com.promade.process.repository.ProcessoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository processoRepository;
    @Autowired
    private ReuService reuService;
    
    public Processo salvar(Processo processo) {
        Optional<Processo> existente = processoRepository.findByNumero(processo.getNumero());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Número de processo já cadastrado.");
        }
        return processoRepository.save(processo);
    }

    public List<Processo> listar() {
        return processoRepository.findAll();
    }

    public void excluir(Long id) {
        processoRepository.deleteById(id);
    }

    public void adicionarReu(Long id, Long reuId) {
        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado"));
        
        Reu reu = reuService.buscarPorId(reuId);
        processo.getReus().add(reu);
        processoRepository.save(processo);
    }
}
