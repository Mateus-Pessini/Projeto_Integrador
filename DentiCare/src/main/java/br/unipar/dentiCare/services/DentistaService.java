package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.Dentista;
import br.unipar.dentiCare.repositories.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

        @Autowired
        DentistaRepository dentistaRepository;

        public Dentista insert(Dentista dentista) throws Exception{
            return dentistaRepository.saveAndFlush(dentista);
        }

        public Dentista edit(Dentista dentista) throws Exception{
            return dentistaRepository.saveAndFlush(dentista);
        }

        public void remove(Long id) throws Exception{
            Dentista dentista = findById(id);
            dentistaRepository.delete(dentista);
        }

        public Dentista findById(Long id) throws Exception{
            Optional<Dentista> retorno = dentistaRepository.findById(id);

            if(retorno.isPresent())
                return retorno.get();
            else
                throw new Exception("Dentista com id " + id + " não identificado");
        }

        public List<Dentista> findAll(){
            return dentistaRepository.findAll();
        }
}
