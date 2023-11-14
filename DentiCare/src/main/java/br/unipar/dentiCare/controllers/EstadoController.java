package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.Estado;
import br.unipar.dentiCare.services.EstadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estado")
@Api(tags = "Endereços", description = "Endpoints para operações relacionadas a endereços")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    @PostMapping
    @ApiOperation(value = "Insere Estado")
    public Estado insert(@Valid @RequestBody Estado estado) throws Exception{
        return estadoService.insert(estado);
    }


    @GetMapping
    @ApiOperation(value = "Lista os Estados")
    public List<Estado> findAll(){
        return estadoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Estado pelo seu id")
    public Estado findById(@PathVariable Long id) throws Exception {
        return estadoService.findById(id);
    }

}
