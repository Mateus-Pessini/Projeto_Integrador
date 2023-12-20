package br.unipar.dentiCare.models.Consulta;

import br.unipar.dentiCare.models.Pessoa.Pessoa;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "Tratamento")
@ApiModel(description = "Objeto Tratamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tratamento {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dentesId")
    private List<Dentes> dentesId;

    @ManyToOne
    @JoinColumn(name = "consultaId")
    private Consulta consultaId;

    private String ds_observacao;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Pessoa clienteId;

}
