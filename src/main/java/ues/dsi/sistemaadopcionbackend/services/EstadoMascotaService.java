package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoMascota;

import java.util.List;

public interface EstadoMascotaService {

    List<EstadoMascota> getAllEstadoMascota();
    EstadoMascota getEstadoMascotaById(Long idEstadoMascota);
    EstadoMascota createEstadoMascota(EstadoMascota estadoMascota);
    EstadoMascota editEstadoMascota(Long idEstadoMascota,EstadoMascota estadoMascota);
    EstadoMascota deleteEstadoMascotaById(Long idEstadoMascota);
}
