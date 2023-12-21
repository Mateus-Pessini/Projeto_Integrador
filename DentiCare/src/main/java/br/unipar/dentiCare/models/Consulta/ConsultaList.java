package br.unipar.dentiCare.models.Consulta;

import br.unipar.dentiCare.models.Pessoa.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.NIP;

import javax.persistence.JoinColumn;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaList {

    private Long id;

    private Pessoa pessoa;

    private String data;
}
