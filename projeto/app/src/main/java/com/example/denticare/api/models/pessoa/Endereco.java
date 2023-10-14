package com.example.denticare.api.models.pessoa;

public class Endereco {

    private Long id;

    private String nmRua;

    private int numero;

    private String bairro;

    private String CEP;

    private String complemento;

    private Cliente cliente;

    private Cidade cidade;

    public Endereco() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmRua() {
        return nmRua;
    }

    public void setNmRua(String nmRua) {
        this.nmRua = nmRua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Endereco(String nmRua, int numero, String bairro, String CEP, String complemento, Cliente cliente, Cidade cidade) {
        this.nmRua = nmRua;
        this.numero = numero;
        this.bairro = bairro;
        this.CEP = CEP;
        this.complemento = complemento;
        this.cliente = cliente;
        this.cidade = cidade;
    }
}
