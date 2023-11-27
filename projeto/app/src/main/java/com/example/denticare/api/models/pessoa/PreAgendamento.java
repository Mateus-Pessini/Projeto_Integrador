package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.enums.TpPessoaEnum;

import java.util.Date;

public class PreAgendamento {
    private Long id;

    private Pessoa pessoa;

    private Date dataAgendamento;

    public PreAgendamento() {
    }

    public PreAgendamento(Long id, Pessoa pessoa, Date dataAgendamento) {
        super();
        this.id = id;
        this.pessoa = pessoa;
        this.dataAgendamento = dataAgendamento;
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

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
