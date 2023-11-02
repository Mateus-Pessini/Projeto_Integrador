package br.unipar.dentiCare.services;

import br.unipar.dentiCare.enums.TpPessoaEnum;
import br.unipar.dentiCare.models.Pessoa.Pessoa;
import br.unipar.dentiCare.models.User.*;
import br.unipar.dentiCare.repositories.PessoaRepository;
import br.unipar.dentiCare.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PessoaService pessoaService;


    public List<UsuarioDTO> findAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getLogin(), usuario.getRole(), usuario.getStatus());
            usuarioDTOs.add(usuarioDTO);
        }
        return usuarioDTOs;
    }


    public UsuarioDTO findById(Long id) throws Exception {

        Optional<Usuario> retorno = usuarioRepository.findUsuarioById(id);

        if(retorno.isPresent()) {
            return new UsuarioDTO(retorno.get().getId(), retorno.get().getLogin(),retorno.get().getRole(),retorno.get().getStatus());
        }else{
            throw new Exception("Usuário com ID " + id + " Não Identificada");
        }
    }


    public UsuarioDTO alterarSenha(UsuarioSenhaDTO usuarioSenhaDTO) throws Exception {

        Optional<Usuario> retorno = usuarioRepository.findUsuarioById(usuarioSenhaDTO.getUsuarioId());
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioSenhaDTO.getNovaSenha());
        retorno.get().setSenha(encryptedPassword);

        usuarioRepository.saveAndFlush(retorno.get());

        return new UsuarioDTO(retorno.get().getId(), retorno.get().getLogin(),retorno.get().getRole(),retorno.get().getStatus());
    }

    public String registrar(RegisterDTO data) throws Exception {

        if (this.usuarioRepository.findByLogin(data.getLogin()) != null) {
            throw new Exception("Usuario já cadastrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getSenha());
        if (data.getPessoaId() == 0|| data.getPessoaId() == null){
            Usuario newUser = new Usuario(data.getLogin(), encryptedPassword, data.getRole(),true);
            this.usuarioRepository.save(newUser);
        }else{
            Pessoa pessoa = pessoaService.findById(data.getPessoaId());
            Usuario newUser = new Usuario(data.getLogin(), encryptedPassword, data.getRole(), true, pessoa);
            this.usuarioRepository.save(newUser);
        }


        return "Usuário cadastrado com sucesso.";

    }


}
