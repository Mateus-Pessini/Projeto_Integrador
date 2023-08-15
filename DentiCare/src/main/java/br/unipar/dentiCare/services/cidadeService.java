package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.Cidade;
import br.unipar.dentiCare.repositories.cidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class cidadeService {

    @Autowired
    cidadeRepository cidadeRepository;

    public Cidade insert(Cidade cidade) throws Exception{
        return cidadeRepository.saveAndFlush(cidade);
    }

    public Cidade edit(Cidade cidade) throws Exception{
        return cidadeRepository.saveAndFlush(cidade);
    }

    public void remove(Long id) throws Exception{
        Cidade cidade = findById(id);
        cidadeRepository.delete(cidade);
    }

    public Cidade findById(Long id) throws Exception{
        Optional<Cidade> retorno = cidadeRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Cidade com id " + id + " não identificado");
    }

    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

}
