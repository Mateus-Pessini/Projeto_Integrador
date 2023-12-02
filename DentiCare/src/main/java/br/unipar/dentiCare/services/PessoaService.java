package br.unipar.dentiCare.services;

import br.unipar.dentiCare.enums.TpPessoaEnum;
import br.unipar.dentiCare.models.Pessoa.*;
import br.unipar.dentiCare.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.unipar.dentiCare.repositories.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    public Pessoa insert(PessoaDTO pessoaDTO, boolean cliente) throws Exception {
        Pessoa pes = pessoaRepository.findOneByCpf(pessoaDTO.getCpf());
        if (pes == null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(pessoaDTO.getNome());
            pessoa.setCpf(pessoaDTO.getCpf());
            pessoa.setRg(pessoaDTO.getRg());
            if (!pessoaDTO.getSenha().isEmpty()) {
                String encryptedPassword = new BCryptPasswordEncoder().encode(pessoaDTO.getSenha());
                pessoa.setSenha(encryptedPassword);
            }
            pessoa.setNrtelefone(pessoaDTO.getNrtelefone());
            pessoa.setEmail(pessoaDTO.getEmail());
            pessoa.setTpPessoa(pessoaDTO.getTpPessoa());
            if (cliente) {
                pessoa.setTpPessoa(TpPessoaEnum.CLIENTE);
            }
            if (!pessoaDTO.getTpPessoa().equals(TpPessoaEnum.CLIENTE)) {
                pessoa.setLogin(pessoaDTO.getEmail());
            }
            pessoa.setCro(pessoaDTO.getCro());
            pessoa.setEspecialidade(pessoaDTO.getEspecialidade());
            //pessoa.setFtPerfil(pessoaDTO.getFtPerfil());
            pessoa.setNmRua(pessoaDTO.getNmRua());
            pessoa.setNumero(pessoaDTO.getNumero());
            pessoa.setCep(pessoaDTO.getCep());
            pessoa.setComplemento(pessoaDTO.getComplemento());
            //TODO pessoa.setCidade(pessoaDTO.getCidadeId());

            pessoa = pessoaRepository.saveAndFlush(pessoa);

            return pessoa;
        } else {
            throw new Exception("Pessoa com este CPF " + pessoaDTO.getCpf() + " já está registrado. Verifique com o consultório!");
        }

    }

    public Pessoa edit(Pessoa pessoa) throws Exception {
        pessoa.setLogin(pessoa.getEmail());
        return pessoaRepository.saveAndFlush(pessoa);
    }

    public Pessoa editDentista(Pessoa pessoa, Long id) throws Exception {
        Pessoa p = findById(id);
        p.setNome(pessoa.getNome());
        p.setNrtelefone(pessoa.getNrtelefone());
        p.setCro(pessoa.getCro());
        p.setEspecialidade(pessoa.getEspecialidade());
        return pessoaRepository.saveAndFlush(p);
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

    public Pessoa findByCpf(String cpf) {
        return pessoaRepository.findOneByCpf(cpf);

        //    throw new Exception("Pessoa com o Cpf " + cpf + " não identificado");
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public List<Pessoa> findByFilters(String nome) {
        return pessoaRepository.findAllByNomeOrderByNomeAsc(nome);
    }
}

