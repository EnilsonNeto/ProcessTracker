package com.promade.process.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Reu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "reus", cascade = CascadeType.ALL)
    private Set<Processo> processos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(Set<Processo> processos) {
        this.processos = processos;
    }
}
