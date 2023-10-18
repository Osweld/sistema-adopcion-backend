package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;

public interface SolicitudAdopcionService {

    Page<SolicitudAdopcion> getAllSolicitudesAdopcion(Pageable pageable);
    Page<SolicitudAdopcion> getAllSolicitudesAdopcionByMascotaId(Long idMascota, Pageable pageable);
    Page<SolicitudAdopcion> getAllSolicitudesAdopcionByUsuarioId(Long idUsuario, Pageable pageable);
    Page<SolicitudAdopcion> getAllSolicitudesAdopcionByEstadoSolicitudAdopcion(Long idEstadoSolicitudAdopcion, Pageable pageable);
    SolicitudAdopcion getSolicitudAdopcionById(Long idSolicitudAdopcion);
    SolicitudAdopcion createSolicitudAdopcion(SolicitudAdopcion solicitudAdopcion);
    SolicitudAdopcion editSolicitudAdopcion(Long idSolicitudAdopcion, SolicitudAdopcion solicitudAdopcion);
    SolicitudAdopcion deleteSolicitudAdopcionById(Long idSolicitudAdopcion);

}
