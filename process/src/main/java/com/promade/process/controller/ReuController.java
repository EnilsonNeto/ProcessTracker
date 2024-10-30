package com.promade.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.promade.process.model.Reu;
import com.promade.process.service.ReuService;

import java.util.List;

@RestController
@RequestMapping("/api/reus")
@CrossOrigin(origins = "http://localhost:4200")
public class ReuController {
    @Autowired
    private ReuService reuService;

    @PostMapping
    public ResponseEntity<Reu> salvar(@RequestBody Reu reu) {
        Reu savedReu = reuService.salvar(reu);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReu);
    }

    @GetMapping
    public List<Reu> listar() {
        return reuService.listar();
    }
}
