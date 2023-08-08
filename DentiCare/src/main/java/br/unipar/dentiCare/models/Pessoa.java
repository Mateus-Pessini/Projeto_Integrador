package br.unipar.dentiCare.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "Paciente")
@ApiModel(description = "Representação de uma Pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID autogerado do Paciente")
    private Long id;

    private String nome



}
