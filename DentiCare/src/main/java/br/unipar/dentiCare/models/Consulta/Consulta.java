package br.unipar.dentiCare.models.Consulta;

import br.unipar.dentiCare.models.Pessoa.Cliente;
import br.unipar.dentiCare.models.Pessoa.Dentista;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Table(name = "Consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dthrConsulta;

    private LocalDateTime dthrAgendamento;

    private boolean stConsulta;

    private Double vlConsulta;

    private String dsConsulta;

    private String nmPessoa;

    private String nrtelefone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clienteId")
    private Cliente clienteId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dentistaId")
    private Dentista dentistaId;
}
