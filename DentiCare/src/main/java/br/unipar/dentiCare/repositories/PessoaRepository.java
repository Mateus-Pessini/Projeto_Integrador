package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query
    List<Pessoa> findAllByNomeOrderByNomeAsc(String nome);

    public Pessoa findOneByCpf(String cpf);

    public Pessoa findFirstByEmail(String email);

    UserDetails findByLogin(String login);

}
