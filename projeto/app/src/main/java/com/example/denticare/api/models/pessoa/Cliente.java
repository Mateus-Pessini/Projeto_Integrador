package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.enums.TpPessoaEnum;

import java.util.List;

public class Cliente extends Pessoa{
    private String CPF;

    private String RG;

    private List<Endereco> enderecos;

    public Cliente() {
    }

    public Cliente(String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa, String CPF, String RG, List<Endereco> enderecos) {
        super(nome, nrtelefone, email, tpPessoa);
        this.CPF = CPF;
        this.RG = RG;
        this.enderecos = enderecos;
    }

    public Cliente(String CPF, String RG, List<Endereco> enderecos) {
        this.CPF = CPF;
        this.RG = RG;
        this.enderecos = enderecos;
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
