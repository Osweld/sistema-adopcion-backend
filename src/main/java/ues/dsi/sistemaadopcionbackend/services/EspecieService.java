package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.entity.Especie;

import java.util.List;

public interface EspecieService {

    List<Especie> getAllEspecies();
    Especie getEspecieById(Long idEspecie);
    Especie createEspecie(Especie Especie);
    Especie editEspecie(Long idEspecie,Especie Especie);
    Especie deleteEspecieById(Long idEspecie);
}
