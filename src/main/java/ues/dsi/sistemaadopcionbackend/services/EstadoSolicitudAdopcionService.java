package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSolicitudAdopcion;

import java.util.List;

public interface EstadoSolicitudAdopcionService {

    List<EstadoSolicitudAdopcion> getAllEstadoSolicitudAdopcion();
    EstadoSolicitudAdopcion getEstadoSolicitudAdopcionById(Long idEstadoSolicitudAdopcion);
    EstadoSolicitudAdopcion createEstadoSolicitudAdopcion(EstadoSolicitudAdopcion estadoSolicitudAdopcion);
    EstadoSolicitudAdopcion editEstadoSolicitudAdopcion(Long idEstadoSolicitudAdopcion,EstadoSolicitudAdopcion estadoSolicitudAdopcion);
    EstadoSolicitudAdopcion deleteEstadoSolicitudAdopcionById(Long idEstadoSolicitudAdopcion);
}
