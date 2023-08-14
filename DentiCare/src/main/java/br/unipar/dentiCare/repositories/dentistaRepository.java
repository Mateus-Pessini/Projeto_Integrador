package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface dentistaRepository extends JpaRepository<Dentista, Long> {
}
