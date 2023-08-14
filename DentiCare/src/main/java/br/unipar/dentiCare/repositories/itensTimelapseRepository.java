package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.itensTimelapse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface itensTimelapseRepository extends JpaRepository<itensTimelapse, Long> {
}
