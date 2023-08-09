package br.unipar.dentiCare.models.Consulta;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "Tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


}
