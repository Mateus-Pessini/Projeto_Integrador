package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100)
    private String nome;

    @Size(min = 9, max = 9)
    private String nrtelefone;

    @Size(min = 3, max = 60)
    private String email;

    private TpPessoaEnum tpPessoa;

}
