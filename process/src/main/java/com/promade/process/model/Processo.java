package com.promade.process.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "processo_reu",
        joinColumns = @JoinColumn(name = "processo_id"),
        inverseJoinColumns = @JoinColumn(name = "reu_id")
    )
    private Set<Reu> reus = new HashSet<>(); // Inicializa o Set

    @Transient // Não será salvo no banco de dados
    private List<Long> reusIds; // Novo atributo para os IDs dos réus

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Set<Reu> getReus() {
        return reus;
    }

    public void setReus(Set<Reu> reus) {
        this.reus = reus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getReusIds() {
        return reusIds;
    }

    public void setReusIds(List<Long> reusIds) {
        this.reusIds = reusIds;
    }
}
