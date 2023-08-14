package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cidadeRepository extends JpaRepository<Cidade, Long> {
}
