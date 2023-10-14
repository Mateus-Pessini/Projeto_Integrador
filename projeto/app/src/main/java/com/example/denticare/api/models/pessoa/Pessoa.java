package com.example.denticare.api.models.pessoa;

import com.example.denticare.api.models.enums.TpPessoaEnum;

public class Pessoa {

        private Long id;

        private String nome;

        private String nrtelefone;

        private String email;

        private TpPessoaEnum tpPessoa;

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

        public Pessoa(String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa) {
                this.nome = nome;
                this.nrtelefone = nrtelefone;
                this.email = email;
                this.tpPessoa = tpPessoa;
        }

        public Pessoa() {
        }
}
