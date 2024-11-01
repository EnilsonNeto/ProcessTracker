package com.promade.process.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.badRequest().build();
        }

        Processo processo = new Processo();
        processo.setNumero(processoDTO.getNumero());

        processo.setReus(obterReusPorIds(processoDTO.getReusIds()));

        Processo processoSalvo = processoService.salvar(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(processoSalvo));
    }

    @GetMapping
    public ResponseEntity<List<ProcessoDTO>> listar() {
        List<ProcessoDTO> processosDTO = processoService.listar().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(processosDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        processoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{processoId}/reus/{reuId}")
    public ResponseEntity<Void> adicionarReu(@PathVariable Long processoId, @PathVariable Long reuId) {
        processoService.adicionarReu(processoId, reuId);
        return ResponseEntity.ok().build();
    }

    private Set<Reu> obterReusPorIds(List<Long> reusIds) {
        return reusIds == null ? Set.of() : reusIds.stream()
            .map(reuService::buscarPorId)
            .filter(reu -> reu != null)
            .collect(Collectors.toSet());
    }

    private ProcessoDTO convertToDto(Processo processo) {
        ProcessoDTO dto = new ProcessoDTO();
        dto.setId(processo.getId());
        dto.setNumero(processo.getNumero());
        dto.setReusIds(processo.getReus().stream()
            .map(Reu::getId)
            .collect(Collectors.toList()));
        return dto;
    }
}
