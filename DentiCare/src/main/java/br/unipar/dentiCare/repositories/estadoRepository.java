package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface estadoRepository extends JpaRepository<Estado, Long> {
}
