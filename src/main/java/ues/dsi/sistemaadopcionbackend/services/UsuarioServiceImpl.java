package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.exceptions.UniqueValidationException;
import ues.dsi.sistemaadopcionbackend.models.DTO.ChangePasswordDTO;
import ues.dsi.sistemaadopcionbackend.models.DTO.UsuarioDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Rol;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.models.repository.UsuarioRepository;

import java.security.Principal;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
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
    public Usuario getUsuarioById(Long idUsuario,Principal principal) {
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
    public Usuario getUsuarioByEmail(String email) {
        if (email == null)
            throw new IllegalArgumentException("El argumento email no puede ser nulo");
        return usuarioRepository.findUsuarioByEmail(email);
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

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));



        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional()
    public Usuario changePassword(ChangePasswordDTO changePasswordDTO, Principal principal) {
        Long id = Long.parseLong(principal.getName());
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrada con el ID proporcionado: "+id));
        if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(),usuario.getPassword())){
            throw new IllegalArgumentException("Introduzca correctamente su contraseña actual");
        }
        usuario.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional()
    public Usuario registerUsuario(Usuario usuario) {
        Rol rol = new Rol(3L);
        usuario.setRol(rol);
        return createUsuario(usuario);
    }

    @Override
    @Transactional
    public Usuario editUsuario(Long idUsuario, UsuarioDTO usuario) {
        if(idUsuario == null || usuario == null){
            throw new IllegalArgumentException("Los argumentos idUsuario y usuario no pueden ser nulos");
        }
        Usuario usuarioDB = usuarioRepository.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrada con el ID proporcionado: "+idUsuario));

        if(usuario.getNombres() != null) usuarioDB.setNombres(usuario.getNombres());
        if(usuario.getApellidos() != null) usuarioDB.setApellidos(usuario.getApellidos());
        if(usuario.getFechaNacimiento() != null) usuarioDB.setFechaNacimiento(usuario.getFechaNacimiento());
        if(usuario.getNumeroDui() != null) {
            if(usuarioRepository.existsUsuarioByNumeroDui(usuario.getNumeroDui() ) && !usuarioDB.getNumeroDui().equals(usuario.getNumeroDui())) {
                throw new UniqueValidationException("Ya existe el usuario con el número de identificación ingresado");
            }
            usuarioDB.setNumeroDui(usuario.getNumeroDui());
        }
        if(usuario.getDireccion() != null) usuarioDB.setDireccion(usuario.getDireccion());
        if(usuario.getEmail() != null) {
            if(usuarioRepository.existsUsuarioByEmail(usuario.getEmail()) && !usuarioDB.getEmail().equals(usuario.getEmail())){
                throw new UniqueValidationException("Ya existe el usuario con el email ingresado");
            }
            usuarioDB.setEmail(usuario.getEmail());
        }
        if(usuario.getTelefono() != null) usuarioDB.setTelefono(usuario.getTelefono());
        if(usuario.getUsername() != null) {
            if(usuarioRepository.existsUsuarioByUsername(usuario.getUsername()) && !usuarioDB.getUsername().equals(usuario.getUsername())){
                throw new UniqueValidationException("Ya existe el usuario con el username ingresado");
            }
            usuarioDB.setUsername(usuario.getUsername());
        }
       // if(usuario.getPassword() != null) usuarioDB.setPassword(usuario.getPassword());
        if(usuario.getRol() != null) usuarioDB.setRol(usuario.getRol());
        if(usuario.getGenero() != null) usuarioDB.setGenero(usuario.getGenero());

        return usuarioRepository.save(usuarioDB);
    }




    @Override
    @Transactional
    public Usuario editPasswordUsuario(Usuario usuario) {
        if(usuario == null){
            throw new IllegalArgumentException("El argumento usuario no puede ser nulo");
        }
        
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
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
