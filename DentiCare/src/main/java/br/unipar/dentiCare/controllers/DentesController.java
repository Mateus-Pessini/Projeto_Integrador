package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Consulta.Dentes;
import br.unipar.dentiCare.services.DentesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/dentes")
@Api(tags = "Pessoas", description = "Rota relacionada com Pessoa")
public class DentesController {

    @Autowired
    DentesService dentesService;

    @PostMapping
    @ApiOperation(value = "Insere um Dente")
    public List<Dentes> insert(@Valid @RequestBody List<Dentes> dentes) throws Exception{
        return dentesService.insert(dentes);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Dente")
    public Dentes edit(@Valid @RequestBody Dentes dentes) throws Exception{
        return dentesService.edit(dentes);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Dente")
    public void remove(@PathVariable Long id) throws Exception{
        dentesService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Dentes")
    public List<Dentes> findAll(){
        return dentesService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Dente pelo id")
    public Dentes findById(@PathVariable Long id) throws Exception{
        return dentesService.findById(id);
    }

}
