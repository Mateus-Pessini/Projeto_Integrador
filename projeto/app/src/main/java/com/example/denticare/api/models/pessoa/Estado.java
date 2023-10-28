package com.example.denticare.api.models.pessoa;

public class Estado {

    private Long id;

    private String nmEstado;


    public Estado() {
    }

    public Estado(String nmEstado) {
        this.nmEstado = nmEstado;
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

}
