package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.Dentista;
import br.unipar.dentiCare.services.DentistaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/dentista")
@Api(tags = "Pessoas", description = "Rota relacionada com Pessoa")
public class DentistaController {

    @Autowired
    DentistaService dentistaService;

    @PostMapping
    @ApiOperation(value = "Insere um Dentista")
    public Dentista insert(@Valid @RequestBody Dentista dentista) throws Exception{
        return dentistaService.insert(dentista);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Dentista")
    public Dentista edit(@Valid @RequestBody Dentista dentista) throws Exception{
        return dentistaService.edit(dentista);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Dentista")
    public void remove(@PathVariable Long id) throws Exception{
        dentistaService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Dentistas")
    public List<Dentista> findAll(){
        return dentistaService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Dentista pelo id")
    public Dentista findById(@PathVariable Long id) throws Exception{
        return dentistaService.findById(id);
    }
}
