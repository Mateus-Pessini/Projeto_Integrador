package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Consulta.Tratamento;
import br.unipar.dentiCare.repositories.tratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class tratamentoService {

    @Autowired
    tratamentoRepository tratamentoRepository;

    public Tratamento insert(Tratamento tratamento) throws Exception{
        return tratamentoRepository.saveAndFlush(tratamento);
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
