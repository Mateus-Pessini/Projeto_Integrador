package br.unipar.dentiCare.models.Consulta;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "Dentes")
public class Dentes {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private int nrDente;

    private String dsDente;
}
