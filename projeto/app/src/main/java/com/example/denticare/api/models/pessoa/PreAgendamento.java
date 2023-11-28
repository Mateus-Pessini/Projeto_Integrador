package com.example.denticare.api.models.pessoa;

import java.time.ZonedDateTime;
import java.util.Date;

public class PreAgendamento {
    private Long id;

    private Pessoa pessoa;

    private String data;

    public PreAgendamento() {
    }

    public PreAgendamento(Long id, Pessoa pessoa, String data) {
        super();
        this.id = id;
        this.pessoa = pessoa;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
