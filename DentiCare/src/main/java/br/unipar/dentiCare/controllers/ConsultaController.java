package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Consulta.Consulta;
import br.unipar.dentiCare.services.ConsultaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/consulta")
@Api(tags = "Consulta", description = "Rota relacionada com Consulta")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @PostMapping
    @ApiOperation(value = "Insere uma Consulta")
    public Consulta insert(@Valid @RequestBody Consulta consulta) throws Exception{
        return consultaService.insert(consulta);
    }

    @PutMapping
    @ApiOperation(value = "Edita uma Consulta")
    public Consulta edit(@Valid @RequestBody Consulta consulta) throws Exception{
        return consultaService.edit(consulta);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove uma Consulta")
    public void remove(@PathVariable Long id) throws Exception{
        consultaService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Consultas")
    public List<Consulta> findAll(){
        return consultaService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca uma Consulta pelo id")
    public Consulta findById(@PathVariable Long id) throws Exception{
        return consultaService.findById(id);
    }


}
