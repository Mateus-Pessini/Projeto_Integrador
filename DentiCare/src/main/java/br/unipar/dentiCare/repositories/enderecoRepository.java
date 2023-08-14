package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface enderecoRepository extends JpaRepository<Endereco, Long> {
}
