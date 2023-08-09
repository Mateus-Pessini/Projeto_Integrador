package br.unipar.dentiCare.models.Pessoa;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@DynamicUpdate
@Table(name = "Estado")
public class Estado {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 3, max = 100)
    private String nmEstado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paisId")
    private Pais pais;

}
