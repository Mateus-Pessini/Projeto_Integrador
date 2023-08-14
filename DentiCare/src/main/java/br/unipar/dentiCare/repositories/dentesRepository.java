package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Consulta.Dentes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface dentesRepository extends JpaRepository<Dentes, Long> {
}
