package br.unipar.dentiCare.models.Pessoa;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@DynamicUpdate
@Table(name = "Cidade")
@ApiModel(description = "Cidade Model")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @ApiModelProperty(value = "Cidade ID")
    private Long id;

    @Size(min = 3, max = 100)
    @ApiModelProperty(value = "Nome da Cidade")
    private String nmCidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estadoId")
    @ApiModelProperty(value = "Estado ao qual a Cidade pertence")
    private Estado estado;
}
