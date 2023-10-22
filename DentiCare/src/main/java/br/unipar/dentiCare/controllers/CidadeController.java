package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.Cidade;
import br.unipar.dentiCare.services.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidade")
@Api(tags = "Endereços", description = "Rota relacionada com Endereço")
public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    @ApiOperation(value = "Insere Cidade")
    @PostMapping
    public Cidade insert(@RequestBody @Valid Cidade cidade) throws Exception{
        return cidadeService.insert(cidade);
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de Cidades")
    public List<Cidade> findAll(){return cidadeService.findAll();}

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna uma Cidade pelo Id")
    public Cidade findById(@PathVariable Long id) throws Exception{
        return cidadeService.findById(id);
    }

    @GetMapping(path = "/{idEstado}")
    @ApiOperation(value = "Retorna uma lista de Cidade pelo Estado")
    public List<Cidade> findByEstado(@PathVariable Long id) throws Exception{
        return cidadeService.findAllByEstado(id);
    }

}
