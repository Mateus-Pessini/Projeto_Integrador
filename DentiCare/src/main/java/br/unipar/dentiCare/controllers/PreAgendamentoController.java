package br.unipar.dentiCare.controllers;

import br.unipar.dentiCare.models.Pessoa.PreAgendamento;
import br.unipar.dentiCare.models.Pessoa.PreAgendamentoDTO;
import br.unipar.dentiCare.services.PreAgendamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
