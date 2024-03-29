package br.unipar.dentiCare.services;

import br.unipar.dentiCare.enums.TpPessoaEnum;
import br.unipar.dentiCare.models.Pessoa.*;
import br.unipar.dentiCare.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.unipar.dentiCare.repositories.PessoaRepository;

import java.util.Collections;
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
        Pessoa pes1 = pessoaRepository.findFirstByEmail(pessoaDTO.getEmail());
        if (pes == null && pes1 == null) {
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
            if (pes1 == null && pes != null) {
                throw new Exception("Cliente/Usuário com este CPF " + pessoaDTO.getCpf() + " já está registrado. Verifique com o consultório!");
            }
            if (pes1 != null && pes == null) {
                throw new Exception("Cliente/Usuário com este E-mail " + pessoaDTO.getEmail() + " informado já está registrado. Verifique com o consultório!");
            }
            if (pes1 != null && pes != null) {
                throw new Exception("Cliente/Usuário com este CPF " + pessoaDTO.getCpf() + " e com este E-mail " + pessoaDTO.getEmail() + " já está registrado. Verifique com o consultório!");
            }
            return null;
        }
    }

    public Pessoa edit(Pessoa pessoa) throws Exception {
        pessoa.setLogin(pessoa.getEmail());
        return pessoaRepository.saveAndFlush(pessoa);
    }
    public Pessoa editClietne(Pessoa pessoa) throws Exception {
        pessoa.setLogin(pessoa.getEmail());
        pessoa.setTpPessoa(TpPessoaEnum.CLIENTE);
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

    public Pessoa findByCpf(String cpf) throws Exception {
        Pessoa retorno = pessoaRepository.findOneByCpf(cpf);

        if (retorno != null)
            return retorno;
        else
            throw new Exception("Pessoa com o Cpf " + cpf + " não identificado");
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }
    public List<Pessoa> findClientes() {
        return pessoaRepository.findAllByTpPessoa(TpPessoaEnum.CLIENTE);
    }
    public List<Pessoa> findByFilters(String nome, TpPessoaEnum tipoPessoa) {
        if (nome == null) {
            return Collections.emptyList();
        }

        if (tipoPessoa != null) {
            return pessoaRepository.findAllByNomeAndTpPessoaOrderByNomeAsc(nome, tipoPessoa);
        } else {
            return pessoaRepository.findAllByNomeOrderByNomeAsc(nome);
        }
    }

}

