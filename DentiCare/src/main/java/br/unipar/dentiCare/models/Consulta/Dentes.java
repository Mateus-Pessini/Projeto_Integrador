package br.unipar.dentiCare.models.Consulta;

import br.unipar.dentiCare.models.Pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "Dentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dentes {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private int nrDente;

    @ManyToOne
    @JoinColumn(name = "dentesId")
    private Tratamento dentes;

    @ManyToOne
    @JoinColumn(name = "denteId")
    @JsonIgnore
    private Pessoa cliente;
}
