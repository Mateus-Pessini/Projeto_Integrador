package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.ItensTimelapse;
import br.unipar.dentiCare.services.ItensTimelapseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/itensTimelapse")
@Api(tags = "Pessoas", description = "Rota relacionada com Pessoas")
public class ItensTimelapseController {

    @Autowired
    ItensTimelapseService itensTimelapseService;

    @PostMapping
    @ApiOperation(value = "Insere um ItensTimelapse")
    public ItensTimelapse insert(@Valid @RequestBody ItensTimelapse itensTimelapse) throws Exception{
        return itensTimelapseService.insert(itensTimelapse);
    }

    @PutMapping
    @ApiOperation(value = "Edita um ItensTimelapse")
    public ItensTimelapse edit(@Valid @RequestBody ItensTimelapse itensTimelapse) throws Exception{
        return itensTimelapseService.edit(itensTimelapse);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um ItensTimelapse")
    public void remove(@PathVariable Long id) throws Exception{
        itensTimelapseService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de ItensTimelapse")
    public List<ItensTimelapse> findAll(){
        return itensTimelapseService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um ItensTimelapse pelo id")
    public ItensTimelapse findById(@PathVariable Long id) throws Exception{
        return itensTimelapseService.findById(id);
    }

}
