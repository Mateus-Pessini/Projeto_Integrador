package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.ItensTimelapse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface itensTimelapseRepository extends JpaRepository<ItensTimelapse, Long> {
}
