package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.entity.Genero;

import java.util.List;

public interface GeneroService {

    List<Genero> getAllGeneros();
    Genero getGeneroById(Long idGenero);
    Genero createGenero(Genero genero);
    Genero editGenero(Long idGenero,Genero genero);
    Genero deleteGeneroById(Long idGenero);

}
