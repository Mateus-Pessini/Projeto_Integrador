package br.unipar.dentiCare.models.Pessoa;

public class TratamentoDTO {

    private Long id;

    private Long dentesId;

    private Long consultaId;

    private String ds_observacao;

    private Long clienteId;

    public TratamentoDTO(Long id, Long dentesId, Long consultaId, String ds_observacao, Long clienteId) {
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

    public Long getDentesId() {
        return dentesId;
    }

    public void setDentesId(Long dentesId) {
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}