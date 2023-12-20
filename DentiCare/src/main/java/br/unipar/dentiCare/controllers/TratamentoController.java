package br.unipar.dentiCare.controllers;


import br.unipar.dentiCare.models.Consulta.Tratamento;
import br.unipar.dentiCare.models.Pessoa.TratamentoDTO;
import br.unipar.dentiCare.services.TratamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/tratamento")
@Api(tags = "Consulta", description = "Rota relacionada com Consulta")
public class TratamentoController {

    @Autowired
    TratamentoService tratamentoService;

    @PostMapping
    @ApiOperation(value = "Insere um Tratamento")
    public Tratamento insert(@Valid @RequestBody TratamentoDTO tratamentoDTO) throws Exception{
        return tratamentoService.insert(tratamentoDTO);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Tratamento")
    public Tratamento edit(@Valid @RequestBody Tratamento tratamento) throws Exception{
        return tratamentoService.edit(tratamento);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Tratamento")
    public void remove(@PathVariable Long id) throws Exception{
        tratamentoService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Tratamentos")
    public List<Tratamento> findAll(){
        return tratamentoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Tratamento pelo id")
    public Tratamento findById(@PathVariable Long id) throws Exception{
        return tratamentoService.findById(id);
    }

}
