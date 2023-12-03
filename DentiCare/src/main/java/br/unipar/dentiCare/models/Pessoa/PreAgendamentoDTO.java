package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PreAgendamentoDTO {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date data;

    private Pessoa pessoa;


    public PreAgendamentoDTO(Long id, Date data, Pessoa pessoa) {
        this.id = id;
        this.data = data;
        this.pessoa = pessoa;
    }

    public PreAgendamentoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
