package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Timelapse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface timelapseRepository extends JpaRepository<Timelapse, Long> {
}
