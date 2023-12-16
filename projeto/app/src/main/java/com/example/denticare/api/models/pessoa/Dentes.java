package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.Tratamentos.Tratamento;

import java.util.List;

public class Dentes {
    private Long id;

    private int nrDente;

    private String dsDente;

    private List<Tratamento> tratamentoId;

    private Pessoa cliente;

    public Dentes(Long id, int nrDente, String dsDente, List<Tratamento> tratamentoId, Pessoa cliente) {
        this.id = id;
        this.nrDente = nrDente;
        this.dsDente = dsDente;
        this.tratamentoId = tratamentoId;
        this.cliente = cliente;
    }

    public Dentes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNrDente() {
        return nrDente;
    }

    public void setNrDente(int nrDente) {
        this.nrDente = nrDente;
    }

    public String getDsDente() {
        return dsDente;
    }

    public void setDsDente(String dsDente) {
        this.dsDente = dsDente;
    }

    public List<Tratamento> getTratamentoId() {
        return tratamentoId;
    }

    public void setTratamentoId(List<Tratamento> tratamentoId) {
        this.tratamentoId = tratamentoId;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }
}
