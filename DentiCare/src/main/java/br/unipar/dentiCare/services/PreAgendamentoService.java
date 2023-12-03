package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.*;
import br.unipar.dentiCare.repositories.CidadeRepository;
import br.unipar.dentiCare.repositories.PreAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.unipar.dentiCare.repositories.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PreAgendamentoService {

    @Autowired
    PreAgendamentoRepository preAgendamentoRepository;


    public PreAgendamento insert(PreAgendamentoDTO preAgendamentoDTO) throws Exception {
        PreAgendamento pre = new PreAgendamento();

        pre.setData(preAgendamentoDTO.getData());
        pre.setPessoa(preAgendamentoDTO.getPessoa());

        pre = preAgendamentoRepository.saveAndFlush(pre);

        return pre;
    }

    public PreAgendamento edit(PreAgendamento preAgendamento) throws Exception {
        return preAgendamentoRepository.saveAndFlush(preAgendamento);
    }

    public void remove(Long id) throws Exception {
        PreAgendamento preAgendamento = findById(id);
        preAgendamentoRepository.delete(preAgendamento);
    }

    public PreAgendamento findById(Long id) throws Exception {
        Optional<PreAgendamento> retorno = preAgendamentoRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("PreAgendamento com id " + id + " não identificado");
    }


    public List<?> findAllWithPeopleName() {
        List<Object[]> x = preAgendamentoRepository.findAllPreAgendamentoWithPeopleName();
        return x;
    }

}

