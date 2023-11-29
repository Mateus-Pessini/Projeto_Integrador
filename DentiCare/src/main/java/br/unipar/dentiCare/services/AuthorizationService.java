package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.User.LoginResponseDTO;
import br.unipar.dentiCare.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    PessoaRepository repository;
    //UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = repository.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário inexistente ou senha inválida");
        }
        return user;
    }

    public LoginResponseDTO buscaDadosUsuarioLogado(String login){
        LoginResponseDTO loginResponseDTO= new LoginResponseDTO();
        return loginResponseDTO;
    }

}
