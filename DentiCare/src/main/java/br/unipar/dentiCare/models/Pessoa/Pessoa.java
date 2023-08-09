package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.tpPessoaEnum;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "Pessoa")
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

    private tpPessoaEnum tpPessoa;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoaId")
    private List<Endereco> enderecos;


}