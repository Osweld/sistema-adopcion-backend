package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ues.dsi.sistemaadopcionbackend.models.DTO.UsuarioDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;

import java.io.IOException;
import java.util.List;

public interface UsuarioService {
    Page<Usuario> getAllUsuarios(Pageable pageable);
    Page<Usuario> getAllUsuariosByGenero(Long idGenero,Pageable pageable);
    Page<Usuario> getAllUsuariosByRol(Long idRol,Pageable pageable);
    Page<Usuario> searchUsuarioByNombres(String nombres, Pageable pageable);
    Usuario getUsuarioById(Long idUsuario);
    Usuario getUsuarioByNumeroDui(String numeroDui);
    Usuario getUsuarioByUsername(String username);
    Boolean getExistsUsuarioByUsername(String username);
    Usuario createUsuario(Usuario usuario);
    Usuario editUsuario(Long idUsuario, UsuarioDTO usuario);
    Usuario deleteUsuarioById(Long idUsuario);
}
