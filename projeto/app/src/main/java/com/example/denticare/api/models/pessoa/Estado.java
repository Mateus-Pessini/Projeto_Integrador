package com.example.denticare.api.models.pessoa;

public class Estado {

    private Long id;

    private String nmEstado;

    private Pais pais;

    public Estado() {
    }

    public Estado(String nmEstado, Pais pais) {
        this.nmEstado = nmEstado;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
