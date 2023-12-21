package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Consulta.Tratamento;
import br.unipar.dentiCare.models.Pessoa.Pessoa;
import br.unipar.dentiCare.models.Pessoa.PreAgendamento;
import br.unipar.dentiCare.models.Pessoa.PreAgendamentoDTO;
import br.unipar.dentiCare.services.PreAgendamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pre-agendamento")
@Api(tags = "PréAgendamento", description = "Rota relacionada com o Pré Agendamento")
public class PreAgendamentoController {

    @Autowired
    PreAgendamentoService preAgendamentoService;

    @PostMapping
    @ApiOperation(value = "Insere um Pré Agendamento")
    public PreAgendamento insert(@Valid @RequestBody PreAgendamentoDTO preAgendamentoDTO) throws Exception{
        return preAgendamentoService.insert(preAgendamentoDTO);
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de PréAgendamentos")
    public List<?> findAll(){
        return preAgendamentoService.findAllWithPeopleName();
    }

    @PutMapping(path = "/{edit}")
    @ApiOperation(value = "Edita um Agendamento")
    public PreAgendamento edit(@Valid @RequestBody PreAgendamento preAgendamento, @PathVariable Long id) throws Exception{
        preAgendamento.setId(id);
        return preAgendamentoService.edit(preAgendamento, preAgendamento.getId());
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Remove um Agendamento")
    public void remove(@PathVariable Long id) throws Exception{
        preAgendamentoService.remove(id);
    }


}
