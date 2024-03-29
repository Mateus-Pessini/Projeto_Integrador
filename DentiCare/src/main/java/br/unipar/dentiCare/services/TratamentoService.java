package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Consulta.Tratamento;
import br.unipar.dentiCare.models.Pessoa.TratamentoDTO;
import br.unipar.dentiCare.repositories.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class TratamentoService {

    @Autowired
    TratamentoRepository tratamentoRepository;

    @Autowired
    PessoaService pessoaService;

    private static final Logger logger = LoggerFactory.getLogger(TratamentoService.class);

    public Tratamento insert(TratamentoDTO tratamentoDTO) throws Exception{
        Tratamento trat = new Tratamento();
        logger.info("Informação de debug: " + tratamentoDTO.getClienteId());
        trat.setClienteId(tratamentoDTO.getClienteId());
        trat.setDentesId(tratamentoDTO.getDentesId());
        trat.setDs_observacao(tratamentoDTO.getDs_observacao());
        return tratamentoRepository.saveAndFlush(trat);
    }

    public Tratamento edit(Tratamento tratamento) throws Exception{
        return tratamentoRepository.saveAndFlush(tratamento);
    }

    public void remove(Long id) throws Exception{
        Tratamento tratamento = findById(id);
        tratamentoRepository.delete(tratamento);
    }

    public Tratamento findById(Long id) throws Exception{
        Optional<Tratamento> retorno = tratamentoRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Tratamento com id " + id + " não identificado");
    }

    public List<Tratamento> findAll(){
        return tratamentoRepository.findAll();
    }

}
