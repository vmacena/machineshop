package br.edu.ifsp.pw3.machineshop.repository;

import br.edu.ifsp.pw3.machineshop.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
