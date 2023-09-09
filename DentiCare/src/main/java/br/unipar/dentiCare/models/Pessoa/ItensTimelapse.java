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
@Table(name = "itensTimelapse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItensTimelapse {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private int nrSequencia;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] ftPerfil;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itensTimelapse")
    private List<Timelapse> Timelapse;
}
