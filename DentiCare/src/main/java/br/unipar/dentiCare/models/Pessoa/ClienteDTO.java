package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;

import javax.validation.constraints.Size;
import java.util.List;

public class ClienteDTO extends PessoaDTO{

    private String CPF;

    private String RG;

    private EnderecoDTO endereco;

    public ClienteDTO(Long id, String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa, EnderecoDTO endereco, String CPF, String RG) {
        super(id, nome, nrtelefone, email, tpPessoa);
        this.endereco = endereco;
        this.CPF = CPF;
        this.RG = RG;
    }

    public ClienteDTO() {
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
}
