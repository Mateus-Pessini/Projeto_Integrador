package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.Pessoa;
import br.unipar.dentiCare.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pessoa")
@Api(tags = "Pessoas", description = "Rota relacionada com Pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    @ApiOperation(value = "Insere um Pessoa")
    public Pessoa insert(@Valid @RequestBody Pessoa pessoa) throws Exception{
        return pessoaService.insert(pessoa);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Pessoa")
    public Pessoa edit(@Valid @RequestBody Pessoa pessoa) throws Exception{
        return pessoaService.edit(pessoa);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Pessoa")
    public void remove(@PathVariable Long id) throws Exception{
        pessoaService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Pessoas")
    public List<Pessoa> findAll(){
        return pessoaService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Pessoa pelo id")
    public Pessoa findById(@PathVariable Long id) throws Exception{
        return pessoaService.findById(id);
    }

}
