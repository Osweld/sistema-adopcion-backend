package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSalud;

import java.util.List;

public interface EstadoSaludService {

    List<EstadoSalud> getAllEstadoSalud();
    EstadoSalud getEstadoSaludById(Long idEstadoSalud);
    EstadoSalud createEstadoSalud(EstadoSalud estadoSalud);
    EstadoSalud editEstadoSalud(Long idEstadoSalud,EstadoSalud estadoSalud);
    EstadoSalud deleteEstadoSaludById(Long idEstadoSalud);
}
