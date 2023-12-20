package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.PreAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreAgendamentoRepository extends JpaRepository<PreAgendamento, Long> {

    @Query(value = "SELECT p.id, p.data, pe.nome FROM pre_agendamento p INNER JOIN PESSOA pe on (p.pessoa_id = pe.id) ORDER BY p.id ASC ", nativeQuery = true)
    public List<Object[]> findAllPreAgendamentoWithPeopleName();


}
