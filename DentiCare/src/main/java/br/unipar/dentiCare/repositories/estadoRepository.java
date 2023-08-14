package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface estadoRepository extends JpaRepository<Estado, Long> {
}
