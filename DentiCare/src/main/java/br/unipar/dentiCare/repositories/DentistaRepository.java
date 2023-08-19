package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
}
