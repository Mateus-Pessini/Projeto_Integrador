package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
