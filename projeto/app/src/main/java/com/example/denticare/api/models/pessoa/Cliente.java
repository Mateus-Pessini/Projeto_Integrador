package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.enums.TpPessoaEnum;

import java.util.List;

public class Cliente extends Pessoa{
    private String CPF;

    private String RG;

    private Endereco endereco;

    private List<Dentes> dentes;

    public Cliente() {
    }

    public Cliente(String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa, String CPF, String RG, Endereco enderecos, List<Dentes> dentes) {
        super(nome, nrtelefone, email, tpPessoa);
        this.CPF = CPF;
        this.RG = RG;
        this.endereco = endereco;
        this.dentes = dentes;
    }

    public Cliente(String CPF, String RG, Endereco enderecos, List<Dentes> dentes) {
        this.CPF = CPF;
        this.RG = RG;
        this.endereco = endereco;
        this.dentes = dentes;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
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
