package com.promade.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.promade.process.dto.ReuDTO;
import com.promade.process.model.Reu;
import com.promade.process.service.ReuService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reus")
@CrossOrigin(origins = "http://localhost:4200")
public class ReuController {
    @Autowired
    private ReuService reuService;

    @PostMapping
    public ResponseEntity<ReuDTO> salvar(@RequestBody ReuDTO reuDTO) {
        Reu reu = new Reu();
        reu.setNome(reuDTO.getNome());
        reu.setCpf(reuDTO.getCpf());
        reu.setEndereco(reuDTO.getEndereco());
        reu.setTelefone(reuDTO.getTelefone());

        Reu savedReu = reuService.salvar(reu);
        
        ReuDTO savedReuDTO = new ReuDTO();
        savedReuDTO.setId(savedReu.getId());
        savedReuDTO.setNome(savedReu.getNome());
        savedReuDTO.setCpf(savedReu.getCpf());
        savedReuDTO.setEndereco(savedReu.getEndereco());
        savedReuDTO.setTelefone(savedReu.getTelefone());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReuDTO);
    }

    @GetMapping
    public List<ReuDTO> listar() {
        return reuService.listar().stream().map(reu -> {
            ReuDTO dto = new ReuDTO();
            dto.setId(reu.getId());
            dto.setNome(reu.getNome());
            dto.setCpf(reu.getCpf());
            dto.setEndereco(reu.getEndereco());
            dto.setTelefone(reu.getTelefone());
            return dto;
        }).collect(Collectors.toList());
    }
}
