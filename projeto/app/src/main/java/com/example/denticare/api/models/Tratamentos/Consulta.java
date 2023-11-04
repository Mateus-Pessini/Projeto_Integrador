package com.example.denticare.api.models.Tratamentos;

import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Dentista;

import java.time.LocalDateTime;

public class Consulta {

    private Long id;

    private LocalDateTime dthrConsulta;

    private LocalDateTime dthrAgendamento;

    private boolean stConsulta;

    private Double vlConsulta;

    private String dsConsulta;

    private String nmPessoa;

    private String nrtelefone;

    private Cliente clienteId;

    private Dentista dentistaId;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime dthrConsulta, LocalDateTime dthrAgendamento, boolean stConsulta,
                    Double vlConsulta, String dsConsulta, String nmPessoa, String nrtelefone, Cliente clienteId, Dentista dentistaId) {
        this.id = id;
        this.dthrConsulta = dthrConsulta;
        this.dthrAgendamento = dthrAgendamento;
        this.stConsulta = stConsulta;
        this.vlConsulta = vlConsulta;
        this.dsConsulta = dsConsulta;
        this.nmPessoa = nmPessoa;
        this.nrtelefone = nrtelefone;
        this.clienteId = clienteId;
        this.dentistaId = dentistaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDthrConsulta() {
        return dthrConsulta;
    }

    public void setDthrConsulta(LocalDateTime dthrConsulta) {
        this.dthrConsulta = dthrConsulta;
    }

    public LocalDateTime getDthrAgendamento() {
        return dthrAgendamento;
    }

    public void setDthrAgendamento(LocalDateTime dthrAgendamento) {
        this.dthrAgendamento = dthrAgendamento;
    }

    public boolean isStConsulta() {
        return stConsulta;
    }

    public void setStConsulta(boolean stConsulta) {
        this.stConsulta = stConsulta;
    }

    public Double getVlConsulta() {
        return vlConsulta;
    }

    public void setVlConsulta(Double vlConsulta) {
        this.vlConsulta = vlConsulta;
    }

    public String getDsConsulta() {
        return dsConsulta;
    }

    public void setDsConsulta(String dsConsulta) {
        this.dsConsulta = dsConsulta;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public String getNrtelefone() {
        return nrtelefone;
    }

    public void setNrtelefone(String nrtelefone) {
        this.nrtelefone = nrtelefone;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Dentista getDentistaId() {
        return dentistaId;
    }

    public void setDentistaId(Dentista dentistaId) {
        this.dentistaId = dentistaId;
    }
}
