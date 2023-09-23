package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

public class PessoaDTO {

    private Long id;

    private String nome;

    private String nrtelefone;

    private String email;

    private TpPessoaEnum tpPessoa;


    public PessoaDTO(Long id, String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa) {
        this.id = id;
        this.nome = nome;
        this.nrtelefone = nrtelefone;
        this.email = email;
        this.tpPessoa = tpPessoa;
    }

    public PessoaDTO() {
    }

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

}
