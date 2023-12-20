package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.models.Consulta.Dentes;

import java.util.List;

public class TratamentoDTO {

    private Long id;

    private List<Dentes> dentesId;

    private Long consultaId;

    private String ds_observacao;

    private Pessoa clienteId;

    public TratamentoDTO() {
    }

    public TratamentoDTO(Long id, List<Dentes> dentesId, Long consultaId, String ds_observacao, Pessoa clienteId) {
        this.id = id;
        this.dentesId = dentesId;
        this.consultaId = consultaId;
        this.ds_observacao = ds_observacao;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dentes> getDentesId() {
        return dentesId;
    }

    public void setDentesId(List<Dentes> dentesId) {
        this.dentesId = dentesId;
    }

    public Long getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Long consultaId) {
        this.consultaId = consultaId;
    }

    public String getDs_observacao() {
        return ds_observacao;
    }

    public void setDs_observacao(String ds_observacao) {
        this.ds_observacao = ds_observacao;
    }

    public Pessoa getClienteId() {
        return clienteId;
    }

    public void setClienteId(Pessoa clienteId) {
        this.clienteId = clienteId;
    }
}
