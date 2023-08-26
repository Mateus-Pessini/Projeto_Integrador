package br.unipar.dentiCare.models.Consulta;

import br.unipar.dentiCare.models.Pessoa.Dentista;
import br.unipar.dentiCare.models.Pessoa.Pessoa;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@DynamicUpdate
@Table(name = "Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Timestamp dthrConsulta;

    private Timestamp dthrAgendamento;

    private boolean stConsulta;

    private Double vlConsulta;

    private String dsConsulta;

    private String nmPessoa;

    private String nrtelefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoaId")
    private Pessoa pessoaId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dentistaId")
    private Dentista dentistaId;
}
