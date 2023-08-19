package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.unipar.dentiCare.repositories.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public Endereco insert(Endereco endereco) throws Exception{
        return enderecoRepository.saveAndFlush(endereco);
    }

    public Endereco edit(Endereco endereco) throws Exception{
        return enderecoRepository.saveAndFlush(endereco);
    }

    public void remove(Long id) throws Exception{
        Endereco endereco = findById(id);
        enderecoRepository.delete(endereco);
    }

    public Endereco findById(Long id) throws Exception{
        Optional<Endereco> retorno = enderecoRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Endereço com id " + id + " não identificado");
    }

    public List<Endereco> findAll(){
        return enderecoRepository.findAll();
    }

}
