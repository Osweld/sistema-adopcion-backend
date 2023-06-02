package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.entity.Rol;

import java.util.List;

public interface RolService {

    List<Rol> getAllRoles();
    Rol getRolById(Long idRol);
    Rol createRol(Rol rol);
    Rol editRol(Long idRol,Rol rol);
    Rol deleteRolById(Long idRol);

}
