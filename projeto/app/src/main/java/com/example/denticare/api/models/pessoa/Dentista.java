package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.enums.TpPessoaEnum;

public class Dentista {
    private String CRO;

    private String especialidade2;

    private byte ftPerfil2;

    public Dentista() {
    }

    public Dentista(String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa, String CRO, String especialidade2, byte ftPerfil2) {
        //super(nome, nrtelefone, email, tpPessoa);
        this.CRO = CRO;
        this.especialidade2 = especialidade2;
        this.ftPerfil2 = ftPerfil2;
    }

    public Dentista(String CRO, String especialidade, byte ftPerfil2) {
        this.CRO = CRO;
        this.especialidade2 = especialidade;
        this.ftPerfil2 = ftPerfil2;
    }

    public String getCRO() {
        return CRO;
    }

    public void setCRO(String CRO) {
        this.CRO = CRO;
    }

    public String getEspecialidade2() {
        return especialidade2;
    }

    public void setEspecialidade2(String especialidade) {
        this.especialidade2 = especialidade2;
    }

    public byte getFtPerfil2() {
        return ftPerfil2;
    }

    public void setFtPerfil2(byte ftPerfil2) {
        this.ftPerfil2 = ftPerfil2;
    }
}
