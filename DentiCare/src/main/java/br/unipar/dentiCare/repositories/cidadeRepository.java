package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cidadeRepository extends JpaRepository<Cidade, Long> {
}
