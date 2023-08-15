package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.ItensTimelapse;
import br.unipar.dentiCare.repositories.itensTimelapseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class itensTimelapseService {

    @Autowired
    itensTimelapseRepository itensTimelapseRepository;

    public ItensTimelapse insert(ItensTimelapse itensTimelapse) throws Exception{
        return itensTimelapseRepository.saveAndFlush(itensTimelapse);
    }

    public ItensTimelapse edit(ItensTimelapse itensTimelapse) throws Exception{
        return itensTimelapseRepository.saveAndFlush(itensTimelapse);
    }

    public void remove(Long id) throws Exception{
        ItensTimelapse itensTimelapse = findById(id);
        itensTimelapseRepository.delete(itensTimelapse);
    }

    public ItensTimelapse findById(Long id) throws Exception{
        Optional<ItensTimelapse> retorno = itensTimelapseRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Item do Timelapse com id " + id + " não identificado");
    }

    public List<ItensTimelapse> findAll(){
        return itensTimelapseRepository.findAll();
    }
}
