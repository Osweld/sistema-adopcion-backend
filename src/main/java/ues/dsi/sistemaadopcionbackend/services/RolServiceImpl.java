package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.entity.Rol;
import ues.dsi.sistemaadopcionbackend.models.repository.RolRepository;

import java.util.List;

@Service
public class RolServiceImpl implements RolService{

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol getRolById(Long idRol) {
        return rolRepository.findById(idRol).orElseThrow(() ->
                new EntityNotFoundException("Rol no encontrado con el Id proporcionado: " + idRol));
    }

    @Override
    @Transactional()
    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional()
    public Rol editRol(Long idRol, Rol rol) {
        if (idRol == null || rol == null) {
            throw new IllegalArgumentException("Los argumentos id del Rol y rol no pueden ser nulos");
        }

        Rol rolDB = rolRepository.findById(idRol).orElseThrow(() ->
                new EntityNotFoundException("Rol no encontrado con el Id proporcionado: " + idRol));
        rolDB.setNombre(rol.getNombre());
        return rolRepository.save(rolDB);
    }

    @Override
    @Transactional()
    public Rol deleteRolById(Long idRol) {
        if (idRol == null) {
            throw new IllegalArgumentException("El argumento id del Rol no puede ser nulo");
        }

        Rol rol = rolRepository.findById(idRol).orElseThrow(() ->
                new EntityNotFoundException("Rol no encontrado con el Id proporcionado: " + idRol));

        rolRepository.delete(rol);
        return rol;
    }
}
