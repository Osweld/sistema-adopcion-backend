package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAllByGeneroId(Long idGenero, Pageable pageable);
    Page<Usuario> findAllByRolId(Long idRol, Pageable pageable);
    Page<Usuario> findUsuariosByNombreIgnoreCase(String nombres,Pageable pageable);
    Usuario findUsuarioByNumDui(String numeroDui);
    Boolean existsUsuarioByNumDui(String numeroDui);
    Usuario findUsuarioByUsername(String username);
    Boolean existsUsuarioByUsername(String username);

}
