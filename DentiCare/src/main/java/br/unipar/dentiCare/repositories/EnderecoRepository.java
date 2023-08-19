package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.Pessoa.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
