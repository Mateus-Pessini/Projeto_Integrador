package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Consulta.Tratamento;
import br.unipar.dentiCare.models.Pessoa.Pessoa;
import br.unipar.dentiCare.models.Pessoa.TratamentoDTO;
import br.unipar.dentiCare.repositories.PessoaRepository;
import br.unipar.dentiCare.repositories.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TratamentoService {

    @Autowired
    TratamentoRepository tratamentoRepository;


    public Tratamento insert(TratamentoDTO tratamentoDTO) throws Exception{
        Tratamento trat = new Tratamento();
        PessoaService pessoaService = new PessoaService();
        Pessoa p = pessoaService.findById(tratamentoDTO.getClienteId());
        trat.setClienteId(p);
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
