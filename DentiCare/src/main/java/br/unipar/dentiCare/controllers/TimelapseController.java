package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.Timelapse;
import br.unipar.dentiCare.services.TimelapseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping(path = "/timelapse")
@Api(tags = "Pessoas", description = "Rota relacionada com Pessoa")
public class TimelapseController {

    @Autowired
    TimelapseService timelapseService;

    @PostMapping
    @ApiOperation(value = "Insere um Timelapse")
    public Timelapse insert(@Valid @RequestBody Timelapse timelapse) throws Exception{
        return timelapseService.insert(timelapse);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Timelapse")
    public Timelapse edit(@Valid @RequestBody Timelapse timelapse) throws Exception{
        return timelapseService.edit(timelapse);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Timelapse")
    public void remove(@PathVariable Long id) throws Exception{
        timelapseService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Timelapses")
    public List<Timelapse> findAll(){
        return timelapseService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Timelapse pelo id")
    public Timelapse findById(@PathVariable Long id) throws Exception{
        return timelapseService.findById(id);
    }

}
