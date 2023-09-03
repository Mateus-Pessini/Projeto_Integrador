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
@RequestMapping(path = "/estado")
@Api(tags = "Endereços", description = "Rota relacionada com Endereço")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    @PostMapping
    @ApiOperation(value = "Insere um Estado")
    public Estado insert(@Valid @RequestBody Estado estado) throws Exception{
        return estadoService.insert(estado);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Estado")
    public Estado edit(@Valid @RequestBody Estado estado) throws Exception{
        return estadoService.edit(estado);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Estado")
    public void remove(@PathVariable Long id) throws Exception{
        estadoService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Estados")
    public List<Estado> findAll(){
        return estadoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Estado pelo id")
    public Estado findById(@PathVariable Long id) throws Exception{
        return estadoService.findById(id);
    }

}
