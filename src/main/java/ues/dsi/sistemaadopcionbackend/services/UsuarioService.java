package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ues.dsi.sistemaadopcionbackend.models.DTO.ChangePasswordDTO;
import ues.dsi.sistemaadopcionbackend.models.DTO.UsuarioDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface UsuarioService {
    Page<Usuario> getAllUsuarios(Pageable pageable);
    Page<Usuario> getAllUsuariosByGenero(Long idGenero,Pageable pageable);
    Page<Usuario> getAllUsuariosByRol(Long idRol,Pageable pageable);
    Page<Usuario> searchUsuarioByNombres(String nombres, Pageable pageable);
    Usuario getUsuarioById(Long idUsuario,Principal principal);
    Usuario getUsuarioByNumeroDui(String numeroDui);
    Usuario getUsuarioByUsername(String username);
    Usuario getUsuarioByEmail(String email);
    Boolean getExistsUsuarioByUsername(String username);
    Usuario createUsuario(Usuario usuario);
    Usuario changePassword(ChangePasswordDTO changePasswordDTO, Principal principal);
    Usuario registerUsuario(Usuario usuario);
    Usuario editUsuario(Long idUsuario, UsuarioDTO usuario);
    Usuario deleteUsuarioById(Long idUsuario);
}
