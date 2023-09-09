package br.unipar.dentiCare.controllers;


import javax.validation.Valid;

import br.unipar.dentiCare.models.User.AuthenticationDTO;
import br.unipar.dentiCare.models.User.LoginResponseDTO;
import br.unipar.dentiCare.models.User.RegisterDTO;
import br.unipar.dentiCare.models.User.Usuario;
import br.unipar.dentiCare.repositories.UsuarioRepository;
import br.unipar.dentiCare.security.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Api(tags = "Autenticação", description = "Endpoints para operações relacionadas a autentição de usuários")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "Realiza Login na Aplicação")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getSenha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @ApiOperation(value = "Registra Login Para a Aplicação")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) throws Exception{

        if (this.repository.findByLogin(data.getLogin()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getSenha());
        Usuario newUser = new Usuario(data.getLogin(), encryptedPassword, data.getRole(), data.getStatus());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
