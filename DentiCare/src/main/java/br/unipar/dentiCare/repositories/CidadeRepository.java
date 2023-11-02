package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Cidade;
import br.unipar.dentiCare.models.Pessoa.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    List<Cidade> findAllByEstado(@Param("estadoId") Estado estado);
}
