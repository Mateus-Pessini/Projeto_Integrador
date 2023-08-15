package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.Pais;
import br.unipar.dentiCare.models.Pessoa.Timelapse;
import br.unipar.dentiCare.repositories.timelapseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class timelapseService {

    @Autowired
    timelapseRepository timelapseRepository;

    public Timelapse insert(Timelapse timelapse) throws Exception{
        return timelapseRepository.saveAndFlush(timelapse);
    }

    public Timelapse edit(Timelapse timelapse) throws Exception{
        return timelapseRepository.saveAndFlush(timelapse);
    }

    public void remove(Long id) throws Exception{
        Timelapse timelapse = findById(id);
        timelapseRepository.delete(timelapse);
    }

    public Timelapse findById(Long id) throws Exception{
        Optional<Timelapse> retorno = timelapseRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Timelapse com id " + id + " não identificado");
    }

    public List<Timelapse> findAll(){
        return timelapseRepository.findAll();
    }
}

