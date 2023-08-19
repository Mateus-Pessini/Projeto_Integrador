package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.Pais;
import br.unipar.dentiCare.services.PaisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pais")
@Api(tags = "Endereços", description = "Rota relacionada com Endereços")
public class PaisController {

    @Autowired
    PaisService paisService;

    @PostMapping
    @ApiOperation(value = "Insere um Pais")
    public Pais insert(@Valid @RequestBody Pais pais) throws Exception{
        return paisService.insert(pais);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Pais")
    public Pais edit(@Valid @RequestBody Pais pais) throws Exception{
        return paisService.edit(pais);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Pais")
    public void remove(@PathVariable Long id) throws Exception{
        paisService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Paises")
    public List<Pais> findAll(){
        return paisService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Pais pelo id")
    public Pais findById(@PathVariable Long id) throws Exception{
        return paisService.findById(id);
    }

}
