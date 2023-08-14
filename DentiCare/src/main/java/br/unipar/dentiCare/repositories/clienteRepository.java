package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface clienteRepository extends JpaRepository<Cliente, Long> {
}
