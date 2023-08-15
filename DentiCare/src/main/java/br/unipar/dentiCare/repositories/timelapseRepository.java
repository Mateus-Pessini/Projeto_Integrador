package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Timelapse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface timelapseRepository extends JpaRepository<Timelapse, Long> {
}
