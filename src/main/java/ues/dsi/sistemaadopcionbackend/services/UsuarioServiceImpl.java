package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.exceptions.UniqueValidationException;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.models.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> getAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> getAllUsuariosByGenero(Long idGenero, Pageable pageable) {
        if (idGenero == null)
            throw new IllegalArgumentException("El argumento idGenero no puede ser nulo");
        return usuarioRepository.findAllByGeneroId(idGenero, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> getAllUsuariosByRol(Long idRol, Pageable pageable) {
        if (idRol == null)
            throw new IllegalArgumentException("El argumento idRol no puede ser nulo");
        return usuarioRepository.findAllByRolId(idRol, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> searchUsuarioByNombres(String nombres, Pageable pageable) {
        if (nombres == null)
            throw new IllegalArgumentException("El argumento nombre no puede ser nulo");
        return usuarioRepository.findUsuariosByNombresIgnoreCase(nombres, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrada con el ID proporcionado: "+idUsuario));
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioByNumeroDui(String numeroDui) {
        if (numeroDui == null)
            throw new IllegalArgumentException("El argumento numero de Dui no puede ser nulo");
        return usuarioRepository.findUsuarioByNumeroDui(numeroDui);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioByUsername(String username) {
        if (username == null)
            throw new IllegalArgumentException("El argumento username no puede ser nulo");
        return usuarioRepository.findUsuarioByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean getExistsUsuarioByUsername(String username) {
        if (username == null)
            throw new IllegalArgumentException("El argumento username no puede ser nulo");
        return usuarioRepository.existsUsuarioByUsername(username);
    }

    @Override
    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        if(usuario == null){
            throw new IllegalArgumentException("El argumento usuario no puede ser nulo");
        }
        if(usuarioRepository.existsUsuarioByNumeroDui(usuario.getNumeroDui())) {
            throw new UniqueValidationException("Ya existe el usuario con el número de identificación ingresado");
        }
        if(usuarioRepository.existsUsuarioByUsername(usuario.getUsername())){
            throw new UniqueValidationException("Ya existe el usuario con el username ingresado");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario editUsuario(Long idUsuario, Usuario usuario) {
        if(idUsuario == null || usuario == null){
            throw new IllegalArgumentException("Los argumentos idUsuario y usuario no pueden ser nulos");
        }
        Usuario usuarioDB = usuarioRepository.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrada con el ID proporcionado: "+idUsuario));

        if(usuario.getNombres() != null) usuarioDB.setNombres(usuario.getNombres());
        if(usuario.getApellidos() != null) usuarioDB.setApellidos(usuario.getApellidos());
        if(usuario.getFechaNacimiento() != null) usuarioDB.setFechaNacimiento(usuario.getFechaNacimiento());
        if(usuario.getNumeroDui() != null) {
            if(usuarioRepository.existsUsuarioByNumeroDui(usuario.getNumeroDui())) {
                throw new UniqueValidationException("Ya existe el usuario con el número de identificación ingresado");
            }
            usuarioDB.setNumeroDui(usuario.getNumeroDui());
        }
        if(usuario.getDireccion() != null) usuarioDB.setDireccion(usuario.getDireccion());
        if(usuario.getEmail() != null) usuarioDB.setEmail(usuario.getEmail());
        if(usuario.getTelefono() != null) usuarioDB.setTelefono(usuario.getTelefono());
        if(usuario.getUsername() != null) {
            if(usuarioRepository.existsUsuarioByUsername(usuario.getUsername())){
                throw new UniqueValidationException("Ya existe el usuario con el username ingresado");
            }
            usuarioDB.setUsername(usuario.getUsername());
        }
        if(usuario.getPassword() != null) usuarioDB.setPassword(usuario.getPassword());
        if(usuario.getRol() != null) usuarioDB.setRol(usuario.getRol());
        if(usuario.getGenero() != null) usuarioDB.setGenero(usuario.getGenero());

        return usuarioRepository.save(usuarioDB);
    }

    @Override
    @Transactional
    public Usuario deleteUsuarioById(Long idUsuario) {
        if (idUsuario == null) {
            throw new IllegalArgumentException("El argumento idUsuario no puede ser nulo");
        }
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new EntityNotFoundException("Usuario no encontrada con el ID proporcionado: " + idUsuario));
        usuarioRepository.delete(usuario);
        return usuario;
    }

}
