package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface pessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query
    List<Pessoa> findAllByNomeOrderByNomeAsc(String nome);


}
