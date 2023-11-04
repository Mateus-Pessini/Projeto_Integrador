package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.enums.TpPessoaEnum;

public class Dentista extends Pessoa{
    private String CRO;

    private String especialidade;

    private byte ftPerfil;

    public Dentista() {
    }

    public Dentista(String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa, String CRO, String especialidade, byte ftPerfil) {
        super(nome, nrtelefone, email, tpPessoa);
        this.CRO = CRO;
        this.especialidade = especialidade;
        this.ftPerfil = ftPerfil;
    }

    public Dentista(String CRO, String especialidade, byte ftPerfil) {
        this.CRO = CRO;
        this.especialidade = especialidade;
        this.ftPerfil = ftPerfil;
    }

    public String getCRO() {
        return CRO;
    }

    public void setCRO(String CRO) {
        this.CRO = CRO;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public byte getFtPerfil() {
        return ftPerfil;
    }

    public void setFtPerfil(byte ftPerfil) {
        this.ftPerfil = ftPerfil;
    }
}
