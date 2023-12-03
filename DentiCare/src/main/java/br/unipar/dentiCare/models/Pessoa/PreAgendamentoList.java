package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.models.User.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreAgendamentoList {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Date data;

    @OneToOne(/*cascade = CascadeType.PERSIST*/)
    @JoinColumn(name = "pessoaId")
    @JsonIgnore
    private Pessoa pessoa;


}
