package com.example.denticare.api.models.pessoa;

public class Pais {

    private Long id;

    private String nmPais;

    public Pais() {
    }

    public Pais(String nmPais) {
        this.nmPais = nmPais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmPais() {
        return nmPais;
    }

    public void setNmPais(String nmPais) {
        this.nmPais = nmPais;
    }
}
