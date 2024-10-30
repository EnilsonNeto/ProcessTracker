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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Processo> salvar(@RequestBody Processo processo) {
        if (processo.getNumero() == null || processo.getNumero().isEmpty()) {
            throw new IllegalArgumentException("Número do processo é obrigatório.");
        }
        
        // Vinculando réus ao processo usando os IDs fornecidos
        if (processo.getReusIds() != null) {
            Set<Reu> reus = new HashSet<>();
            for (Long reuId : processo.getReusIds()) {
                Reu reu = reuService.buscarPorId(reuId); // Busca o réu pelo ID
                if (reu != null) {
                    reus.add(reu);
                }
            }
            processo.setReus(reus);
        }

        processoService.salvar(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(processo);
    }
    
    @GetMapping
    public List<Processo> listar() {
        return processoService.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        processoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reu")
    public ResponseEntity<Void> adicionarReu(@PathVariable Long id, @RequestParam Long reuId) {
        processoService.adicionarReu(id, reuId);
        return ResponseEntity.ok().build();
    }
}
