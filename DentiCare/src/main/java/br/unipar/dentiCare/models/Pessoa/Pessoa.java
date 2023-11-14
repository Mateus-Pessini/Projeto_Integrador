package br.unipar.dentiCare.models.Pessoa;

import br.unipar.dentiCare.enums.TpPessoaEnum;
import br.unipar.dentiCare.models.Consulta.Dentes;
import br.unipar.dentiCare.models.User.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Size(min = 11, max = 11)
    private String cpf;

    @Size(min = 9, max = 9)
    private String rg;

    @Size(min = 9, max = 9)
    private String nrtelefone;

    @Size(min = 3, max = 60)
    private String email;

    private TpPessoaEnum tpPessoa;

    private String cro;

    private String especialidade;

    //@Lob
    //@Basic(fetch = FetchType.LAZY)
    //private byte ftPerfil;

    // Endereço
    @Size(min = 3, max = 200)
    private String nmRua;

    private int numero;

    @Size(min = 3, max = 200)
    private String bairro;

    @Size(min = 8, max = 8)
    private String cep;

    private String complemento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidadeId")
    @JsonIgnore
    private Cidade cidade;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private Usuario user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "denteId")
    @JsonIgnore
    private List<Dentes> dentes;

}
