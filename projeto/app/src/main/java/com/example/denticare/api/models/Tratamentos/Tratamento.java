package com.example.denticare.api.models.Tratamentos;

import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Dentes;
import com.example.denticare.api.models.pessoa.Pessoa;

import java.util.List;

public class Tratamento {
    private Long id;

    private List<Dentes> dentesId;

    private Consulta consultaId;

    private String ds_observacao;

    private Pessoa clienteId;

    public Tratamento() {
    }

    public Tratamento(Long id, List<Dentes> dentesId, Consulta consultaId, String ds_observacao, Pessoa cliente) {
        this.id = id;
        this.dentesId = dentesId;
        this.consultaId = consultaId;
        this.ds_observacao = ds_observacao;
        this.clienteId = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dentes> getDentesId() {
        return dentesId;
    }

    public void setDentesId(List<Dentes> dentesId) {
        this.dentesId = dentesId;
    }

    public Consulta getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Consulta consultaId) {
        this.consultaId = consultaId;
    }

    public String getDs_observacao() {
        return ds_observacao;
    }

    public void setDs_observacao(String ds_observacao) {
        this.ds_observacao = ds_observacao;
    }

    public Pessoa getCliente() {
        return clienteId;
    }

    public void setCliente(Pessoa cliente) {
        this.clienteId = cliente;
    }
}
