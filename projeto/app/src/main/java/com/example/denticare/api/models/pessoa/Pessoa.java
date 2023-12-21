package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.enums.TpPessoaEnum;

import java.util.List;

public class Pessoa {

        private Long id;

        private String nome;

        private String nrtelefone;

        private String email;

        private String senha;

        private TpPessoaEnum tpPessoa;

        private String cpf;

        private String rg;

        private Endereco endereco;

        private String cro;

        private String especialidade;

        private byte ftPerfil;

        private String nmRua;

        private int numero;

        private String bairro;

        private String cep;

        private String complemento;

        private Cliente cliente;

        private Cidade cidade;
        private List<Dentes> dentes;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getNrtelefone() {
                return nrtelefone;
        }

        public void setNrtelefone(String nrtelefone) {
                this.nrtelefone = nrtelefone;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public TpPessoaEnum getTpPessoa() {
                return tpPessoa;
        }

        public void setTpPessoa(TpPessoaEnum tpPessoa) {
                this.tpPessoa = tpPessoa;
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

        public String getCro() {
                return cro;
        }

        public void setCro(String cro) {
                this.cro = cro;
        }

        public String getEspecialidade() {
                return especialidade;
        }

        public void setEspecialidade(String especialidade) {
                this.especialidade = especialidade;
        }

        /*public byte getFtPerfil() {
                return ftPerfil;
        }*/

        public void setFtPerfil(byte ftPerfil) {
                this.ftPerfil = ftPerfil;
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

        public String getCep() {
                return cep;
        }

        public void setCep(String cep) {
                this.cep = cep;
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

        public List<Dentes> getDentes() {
                return dentes;
        }

        public void setDentes(List<Dentes> dentes) {
                this.dentes = dentes;
        }

        public String getSenha() {
                return senha;
        }

        public void setSenha(String senha) {
                this.senha = senha;
        }

        public Pessoa(Long id, String nome, String cpf, String rg, String nrtelefone, String email, String senha, TpPessoaEnum tpPessoa, String cro, String especialidade,
                      String nmRua, int numero, String bairro, String cep, String complemento, Cidade cidade) {
                this.id = id;
                this.nome = nome;
                this.cpf = cpf;
                this.rg = rg;
                this.nrtelefone = nrtelefone;
                this.email = email;
                this.senha = senha;
                this.tpPessoa = tpPessoa;
                this.cro = cro;
                this.especialidade = especialidade;
                //this.ftPerfil = ftPerfil;
                this.nmRua = nmRua;
                this.bairro = bairro;
                this.numero = numero;
                this.cep = cep;
                this.complemento = complemento;
                this.cidade = cidade;
        }

        public Pessoa() {
        }

        @Override
        public String toString() {
                return "Pessoa{" +
                        "id=" + id +
                        ", nome='" + nome + '\'' +
                        ", tpPessoa=" + tpPessoa +
                        ", cpf='" + cpf + '\'' +
                        ", rg='" + rg + '\'' +
                        '}';
        }
}
