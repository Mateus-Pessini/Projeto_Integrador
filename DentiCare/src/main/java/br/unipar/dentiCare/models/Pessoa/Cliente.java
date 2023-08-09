package br.unipar.dentiCare.models.Pessoa;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@DynamicUpdate
@Table(name = "Cliente")
public class Cliente extends Pessoa{

    @Size(min = 11, max = 11)
    private String CPF;

    @Size(min = 9, max = 9)
    private String RG;
}
