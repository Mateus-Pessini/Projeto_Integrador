package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.Cliente;
import br.unipar.dentiCare.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente insert(Cliente cliente) throws Exception{
        return clienteRepository.saveAndFlush(cliente);
    }

    public Cliente edit(Cliente cliente) throws Exception{
        return clienteRepository.saveAndFlush(cliente);
    }

    public void remove(Long id) throws Exception{
        Cliente cliente = findById(id);
        clienteRepository.delete(cliente);
    }

    public Cliente findById(Long id) throws Exception{
        Optional<Cliente> retorno = clienteRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Cliente com id " + id + " não identificado");
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

}
