package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.Cliente;
import br.unipar.dentiCare.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/cliente")
@Api(tags = "Pessoas", description = "Rota relacionada com Pessoa")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    @ApiOperation(value = "Insere um Cliente")
    public Cliente insert(@Valid @RequestBody Cliente cliente) throws Exception{
        return clienteService.insert(cliente);
    }

    @PutMapping
    @ApiOperation(value = "Edita um Cliente")
    public Cliente edit(@Valid @RequestBody Cliente cliente) throws Exception{
        return clienteService.edit(cliente);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Cliente")
    public void remove(@PathVariable Long id) throws Exception{
        clienteService.remove(id);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de Clientes")
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca um Cliente pelo id")
    public Cliente findById(@PathVariable Long id) throws Exception{
        return clienteService.findById(id);
    }
}
