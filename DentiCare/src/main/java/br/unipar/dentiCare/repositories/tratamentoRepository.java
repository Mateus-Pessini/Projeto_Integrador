package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Consulta.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tratamentoRepository extends JpaRepository<Tratamento, Long> {
}
