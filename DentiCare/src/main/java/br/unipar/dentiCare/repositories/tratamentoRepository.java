package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Consulta.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tratamentoRepository extends JpaRepository<Tratamento, Long> {
}
