package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.User.*;
import br.unipar.dentiCare.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
@Api(tags = "Usuários", description ="Endpoints relativos aos usuários do sistema")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de usuários")
    public List<UsuarioDTO> findAll(){

        return usuarioService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna os dados de um usuário")
    public UsuarioDTO findById(@PathVariable Long id) throws Exception{
        return usuarioService.findById(id);
    }

    @PutMapping
    @ApiOperation(value = "Altera a Senha do Usuário")
    public UsuarioDTO alterarSenha(@RequestBody UsuarioSenhaDTO usuarioSenhaDTO) throws Exception{
        return usuarioService.alterarSenha(usuarioSenhaDTO);
    }

}
