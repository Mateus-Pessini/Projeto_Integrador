package com.example.denticare.api.models.pessoa;

public class ConsultaList {

    private Long id;

    private String nome;

    private String data;

    public ConsultaList() {
    }

    public ConsultaList(Long id, String nome, String data) {
        super();
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
