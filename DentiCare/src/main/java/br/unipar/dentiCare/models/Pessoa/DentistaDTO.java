package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import java.util.List;

public class DentistaDTO extends PessoaDTO{

    private String CRO;

    private String especialidade;

    private byte ftPerfil;

    public DentistaDTO() {
    }

    public DentistaDTO(Long id, String nome, String nrtelefone, String email, TpPessoaEnum tpPessoa, String CRO, String especialidade, byte ftPerfil) {
        super(id, nome, nrtelefone, email, tpPessoa);
        this.CRO = CRO;
        this.especialidade = especialidade;
        this.ftPerfil = ftPerfil;
    }

    public String getCRO() {
        return CRO;
    }

    public void setCRO(String CRO) {
        this.CRO = CRO;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public byte getFtPerfil() {
        return ftPerfil;
    }

    public void setFtPerfil(byte ftPerfil) {
        this.ftPerfil = ftPerfil;
    }
}
