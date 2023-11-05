package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;

import javax.validation.constraints.Size;
import java.util.List;

public class ClienteDTO extends PessoaDTO{

    private String cpf;

    private String rg;

    private EnderecoDTO endereco;

    public ClienteDTO(Long id, String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa, EnderecoDTO endereco, String cpf, String rg) {
        super(id, nome, nrtelefone, email, tpPessoa);
        this.endereco = endereco;
        this.cpf = cpf;
        this.rg = rg;
    }

    public ClienteDTO() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }
}
