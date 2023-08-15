package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.Cidade;
import br.unipar.dentiCare.models.Pessoa.Pais;
import br.unipar.dentiCare.repositories.paisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class paisService {

    @Autowired
    paisRepository paisRepository;

    public Pais insert(Pais pais) throws Exception{
        return paisRepository.saveAndFlush(pais);
    }

    public Pais edit(Pais pais) throws Exception{
        return paisRepository.saveAndFlush(pais);
    }

    public void remove(Long id) throws Exception{
        Pais pais = findById(id);
        paisRepository.delete(pais);
    }

    public Pais findById(Long id) throws Exception{
        Optional<Pais> retorno = paisRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("País com id " + id + " não identificado");
    }

    public List<Pais> findAll(){
        return paisRepository.findAll();
    }
}
