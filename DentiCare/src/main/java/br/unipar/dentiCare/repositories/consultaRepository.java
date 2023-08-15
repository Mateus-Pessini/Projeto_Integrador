package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface consultaRepository extends JpaRepository<Consulta, Long> {
}
