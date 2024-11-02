package com.promade.process.dto;

import java.util.List;

public class ProcessoDTO {
    private Long id;
    private String numero;
    private List<Long> reusIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Long> getReusIds() {
        return reusIds;
    }

    public void setReusIds(List<Long> reusIds) {
        this.reusIds = reusIds;
    }
}
