package com.promade.process.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promade.process.dto.ProcessoDTO;
import com.promade.process.model.Processo;
import com.promade.process.model.Reu;
import com.promade.process.service.ProcessoService;
import com.promade.process.service.ReuService;

@RestController
@RequestMapping("/api/processos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ReuService reuService;

    @PostMapping
    public ResponseEntity<ProcessoDTO> salvar(@RequestBody ProcessoDTO processoDTO) {
        if (processoDTO.getNumero() == null || processoDTO.getNumero().isEmpty()) {
            throw new IllegalArgumentException("Número do processo é obrigatório.");
        }

        Processo processo = new Processo();
        processo.setNumero(processoDTO.getNumero());

        if (processoDTO.getReusIds() != null) {
            Set<Reu> reus = new HashSet<>();
            for (Long reuId : processoDTO.getReusIds()) {
                Reu reu = reuService.buscarPorId(reuId);
                if (reu != null) {
                    reus.add(reu);
                }
            }
            processo.setReus(reus);
        }

        Processo processoSalvo = processoService.salvar(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(processoSalvo));
    }

    @GetMapping
    public ResponseEntity<List<ProcessoDTO>> listar() {
        List<Processo> processos = processoService.listar();
        List<ProcessoDTO> processosDTO = processos.stream()
            .map(this::convertToDto)
            .toList();
        return ResponseEntity.ok(processosDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable long id) {
        processoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{processoId}/reus/{reuId}")
    public ResponseEntity<Void> adicionarReu(@PathVariable long processoId, @PathVariable long reuId) {
        processoService.adicionarReu(processoId, reuId);
        return ResponseEntity.ok().build();
    }

    // Métodos auxiliares para conversão
    private ProcessoDTO convertToDto(Processo processo) {
        ProcessoDTO dto = new ProcessoDTO();
        dto.setId(processo.getId());
        dto.setNumero(processo.getNumero());
        dto.setReusIds(processo.getReus().stream().map(Reu::getId).toList());
        return dto;
    }
}