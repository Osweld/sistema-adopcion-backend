package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.entity.Raza;

import java.util.List;

public interface RazaService {

    List<Raza> getAllRazas();
    Raza getRazaById(Long idRaza);
    Raza createRaza(Raza raza);
    Raza editRaza(Long idRaza,Raza raza);
    Raza deleteRazaById(Long idRaza);
}
