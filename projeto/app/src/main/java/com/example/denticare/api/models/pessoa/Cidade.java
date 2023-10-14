package com.example.denticare.api.models.pessoa;

public class Cidade {


    private Long id;

    private String nmCidade;

    private Estado estado;

    public Long getId() {
        return id;
    }

    public Cidade() {
    }

    public Cidade(String nmCidade, Estado estado) {
        this.nmCidade = nmCidade;
        this.estado = estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
