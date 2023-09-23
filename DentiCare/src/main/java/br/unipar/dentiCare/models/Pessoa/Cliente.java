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
@Table(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa{

    @Size(min = 11, max = 11)
    private String CPF;

    @Size(min = 9, max = 9)
    private String RG;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "clienteId")
    private List<Endereco> enderecos;

}
