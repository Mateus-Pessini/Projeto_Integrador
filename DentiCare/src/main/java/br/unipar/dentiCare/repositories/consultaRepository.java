package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface consultaRepository extends JpaRepository<Consulta, Long> {
}
