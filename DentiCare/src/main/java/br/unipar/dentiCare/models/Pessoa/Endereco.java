package br.unipar.dentiCare.models.Pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@DynamicUpdate
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 200)
    private String nmRua;

    private int numero;

    @Size(min = 8, max = 8)
    private String CEP;

    private String complemento;

    @ManyToOne
    @JoinColumn(name = "pessoaId")
    @JsonIgnore
    private Pessoa pessoa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidadeId")
    private Cidade cidade;
}
