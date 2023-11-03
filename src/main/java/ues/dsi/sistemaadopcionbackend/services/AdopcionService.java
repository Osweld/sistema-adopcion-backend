package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ues.dsi.sistemaadopcionbackend.models.DTO.GenerateAdopcionDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Adopcion;

public interface AdopcionService {

    Adopcion createAdopcion(GenerateAdopcionDTO generateAdopcionDTO);
    Adopcion deleteAdopcion(Long idAdopcion);
    Page<Adopcion> getAllAdopciones(Pageable pageable);
}
