package br.unipar.dentiCare.services;

import br.unipar.dentiCare.controllers.AuthenticationController;
import br.unipar.dentiCare.enums.TpPessoaEnum;
import br.unipar.dentiCare.models.Pessoa.Pessoa;
import br.unipar.dentiCare.models.User.RegisterDTO;
import br.unipar.dentiCare.models.User.Usuario;
import br.unipar.dentiCare.models.User.UsuarioRole;
import br.unipar.dentiCare.repositories.PessoaRepository;
import br.unipar.dentiCare.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    AuthenticationController authenticationController;

    @Override
    public void run(ApplicationArguments args) throws Exception {



        if (usuarioRepository.count() == 0) {

            Pessoa pessoa = new Pessoa();
            pessoa.setTpPessoa(TpPessoaEnum.DENTISTA);
            pessoa.setNome("admin");
            pessoa.setEmail("admin");
            pessoa.setNrtelefone("123456789");

            Usuario registerDTO = new Usuario();

            registerDTO.setLogin("admin");
            registerDTO.setRole(UsuarioRole.DENTISTA);
            registerDTO.setStatus(true);
            registerDTO.setPessoa(pessoa);

            String encryptedPassword = new BCryptPasswordEncoder().encode("admin");

            registerDTO.setSenha(encryptedPassword);
            usuarioRepository.saveAndFlush(registerDTO);
        }
    }



}
