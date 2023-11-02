package br.unipar.dentiCare.repositories;

import br.unipar.dentiCare.models.User.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findUsuarioById(@Param("id") Long id);

    UserDetails findByLogin(String login);
}
