package br.unipar.dentiCare.models.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "Timelapse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Timelapse {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoaId")
    private Pessoa pessoa;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itensTimelapseId")
    private List<ItensTimelapse>  itensTimelapse;
}
