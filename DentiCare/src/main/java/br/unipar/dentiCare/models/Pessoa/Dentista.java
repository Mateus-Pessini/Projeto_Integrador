package br.unipar.dentiCare.models.Pessoa;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "Dentista")
public class Dentista extends Pessoa{

    private String CRO;

    private String especialidade;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte ftPerfil;

}
