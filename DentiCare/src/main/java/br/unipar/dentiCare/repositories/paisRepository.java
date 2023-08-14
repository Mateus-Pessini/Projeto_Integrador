package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface paisRepository extends JpaRepository<Pais, Long> {
}
