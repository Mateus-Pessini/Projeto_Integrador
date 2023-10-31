package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.User.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Usuario findUsuarioById(@Param("id") Long id);

    UserDetails findByLogin(String login);
}
