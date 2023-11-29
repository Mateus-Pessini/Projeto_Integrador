package br.unipar.dentiCare.models.User;

import br.unipar.dentiCare.enums.TpPessoaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
    //private UsuarioRole role;
    private TpPessoaEnum tpPessoa;



}
