package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.enums.TpPessoaEnum;

import java.util.List;

public class Cliente {
    private String cpf;

    private String rg;

    private Endereco endereco;

    private List<Dentes> dentes;

    public Cliente() {
    }

    public Cliente(String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa, String cpf, String rg, Endereco endereco, List<Dentes> dentes) {
        //super(nome, nrtelefone, email, tpPessoa);
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.dentes = dentes;
    }

    public Cliente(String cpf, String rg, Endereco endereco, List<Dentes> dentes) {
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.dentes = dentes;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Dentes> getDentes() {
        return dentes;
    }

    public void setDentes(List<Dentes> dentes) {
        this.dentes = dentes;
    }
}
