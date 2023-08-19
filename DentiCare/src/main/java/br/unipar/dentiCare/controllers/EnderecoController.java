package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.Endereco;
import br.unipar.dentiCare.services.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/endereco")
@Api(tags = "Endereços", description = "Rota relacionada com Endereço")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping
    @ApiOperation(value = "Insere um Endereço")
    public Endereco insert(@Valid @RequestBody Endereco endereco) throws Exception{
        return enderecoService.insert(endereco);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Endereço")
    public Endereco edit(@Valid @RequestBody Endereco endereco) throws Exception{
        return enderecoService.edit(endereco);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Endereço")
    public void remove(@PathVariable Long id) throws Exception{
        enderecoService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Endereços")
    public List<Endereco> findAll(){
        return enderecoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Endereço pelo id")
    public Endereco findById(@PathVariable Long id) throws Exception{
        return enderecoService.findById(id);
    }

}
