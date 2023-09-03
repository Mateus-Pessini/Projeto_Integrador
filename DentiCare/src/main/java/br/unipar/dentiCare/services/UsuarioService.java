package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.User.*;
import br.unipar.dentiCare.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

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

        Usuario retorno = usuarioRepository.findUsuarioById(id);

        if(retorno!= null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(retorno.getId(), retorno.getLogin(), retorno.getRole(), retorno.getStatus());
            return usuarioDTO;
        }else{
            throw new Exception("Usuário com ID " + id + " Não Identificada");
        }
    }


    public UsuarioDTO alterarSenha(UsuarioSenhaDTO usuarioSenhaDTO) throws Exception {

        Usuario usuario = usuarioRepository.findUsuarioById(usuarioSenhaDTO.getUsuarioId());
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioSenhaDTO.getNovaSenha());
        usuario.setSenha(encryptedPassword);

        usuarioRepository.saveAndFlush(usuario);

        return findById(usuario.getId());
    }

}
