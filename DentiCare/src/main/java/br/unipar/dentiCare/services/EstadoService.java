package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.Estado;
import br.unipar.dentiCare.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    public Estado insert(Estado estado) throws Exception{
        return estadoRepository.saveAndFlush(estado);
    }

    public Estado edit(Estado estado) throws Exception{
        return estadoRepository.saveAndFlush(estado);
    }

    public void remove(Long id) throws Exception{
        Estado estado = findById(id);
        estadoRepository.delete(estado);
    }

    public Estado findById(Long id) throws Exception{
        Optional<Estado> retorno = estadoRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Estado com id " + id + " não identificado");
    }

    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }
}
