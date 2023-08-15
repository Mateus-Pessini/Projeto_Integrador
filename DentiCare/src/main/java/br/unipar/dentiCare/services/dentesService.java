package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Consulta.Dentes;
import br.unipar.dentiCare.repositories.dentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class dentesService {

    @Autowired
    dentesRepository dentesRepository;

    public Dentes insert(Dentes dentes) throws Exception{
        return dentesRepository.saveAndFlush(dentes);
    }

    public Dentes edit(Dentes dentes) throws Exception{
        return dentesRepository.saveAndFlush(dentes);
    }

    public void remove(Long id) throws Exception{
        Dentes dentes = findById(id);
        dentesRepository.delete(dentes);
    }

    public Dentes findById(Long id) throws Exception{
        Optional<Dentes> retorno = dentesRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Dente com id " + id + " não identificado");
    }

    public List<Dentes> findAll(){
        return dentesRepository.findAll();
    }

}
