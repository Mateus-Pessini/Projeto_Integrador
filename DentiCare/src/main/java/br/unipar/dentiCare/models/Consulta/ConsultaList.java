package br.unipar.dentiCare.models.Consulta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.NIP;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaList {

    private Long id;

    private String nome;

    private String data;
}
