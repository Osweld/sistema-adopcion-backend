package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoCitaSolicitud;

import java.util.List;

public interface EstadoCitaSolicitudService {

    List<EstadoCitaSolicitud> getAllEstadoCitaSolicitud();
    EstadoCitaSolicitud getEstadoCitaSolicitudById(Long idEstadoCitaSolicitud);
    EstadoCitaSolicitud createEstadoCitaSolicitud(EstadoCitaSolicitud estadoCitaSolicitud);
    EstadoCitaSolicitud editEstadoCitaSolicitud(Long idEstadoCitaSolicitud,EstadoCitaSolicitud estadoCitaSolicitud);
    EstadoCitaSolicitud deleteEstadoCitaSolicitudById(Long idEstadoCitaSolicitud);
}
