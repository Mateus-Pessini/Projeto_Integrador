package br.unipar.dentiCare.models.Consulta;

import br.unipar.dentiCare.models.Pessoa.Pessoa;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "Tratamento")
@ApiModel(description = "Objeto Tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dentesId")
    private List<Dentes> dentesId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultaId")
    private Consulta consultaId;

    private String ds_observacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoaId")
    private Pessoa pessoaId;

    public Tratamento() {
    }

    public Tratamento(Long id, List<Dentes> dentesId, Consulta consultaId, String ds_observacao, Pessoa pessoaId) {
        this.id = id;
        this.dentesId = dentesId;
        this.consultaId = consultaId;
        this.ds_observacao = ds_observacao;
        this.pessoaId = pessoaId;
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

    public Consulta getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(Consulta consultaId) {
        this.consultaId = consultaId;
    }

    public String getDs_observacao() {
        return ds_observacao;
    }

    public void setDs_observacao(String ds_observacao) {
        this.ds_observacao = ds_observacao;
    }

    public Pessoa getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Pessoa pessoaId) {
        this.pessoaId = pessoaId;
    }
}
