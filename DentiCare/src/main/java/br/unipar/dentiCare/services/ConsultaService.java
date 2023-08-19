package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Consulta.Consulta;
import br.unipar.dentiCare.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    public Consulta insert(Consulta consulta) throws Exception{
        return consultaRepository.saveAndFlush(consulta);
    }

    public Consulta edit(Consulta consulta) throws Exception{
        return consultaRepository.saveAndFlush(consulta);
    }

    public void remove(Long id) throws Exception{
        Consulta consulta = findById(id);
        consultaRepository.delete(consulta);
    }

    public Consulta findById(Long id) throws Exception{
        Optional<Consulta> retorno = consultaRepository.findById(id);

        if(retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Consulta com id " + id + " não identificado");
    }

    public List<Consulta> findAll(){
        return consultaRepository.findAll();
    }


}
