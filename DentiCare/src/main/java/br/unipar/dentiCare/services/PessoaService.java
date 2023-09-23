package br.unipar.dentiCare.services;

import br.unipar.dentiCare.models.Pessoa.*;
import br.unipar.dentiCare.repositories.CidadeRepository;
import br.unipar.dentiCare.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.unipar.dentiCare.repositories.PessoaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    public Pessoa insert(PessoaDTO pessoaDTO) throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setNrtelefone(pessoaDTO.getNrtelefone());
        pessoa.setEmail(pessoaDTO.getEmail());
        pessoa.setTpPessoa(pessoaDTO.getTpPessoa());

        pessoa = pessoaRepository.saveAndFlush(pessoa);

        return pessoa;
    }

    public Pessoa edit(Pessoa pessoa) throws Exception {
        return pessoaRepository.saveAndFlush(pessoa);
    }

    public void remove(Long id) throws Exception {
        Pessoa pessoa = findById(id);
        pessoaRepository.delete(pessoa);
    }

    public Pessoa findById(Long id) throws Exception {
        Optional<Pessoa> retorno = pessoaRepository.findById(id);

        if (retorno.isPresent())
            return retorno.get();
        else
            throw new Exception("Pessoa com id " + id + " não identificado");
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public List<Pessoa> findByFilters(String nome) {
        return pessoaRepository.findAllByNomeOrderByNomeAsc(nome);
    }
}

