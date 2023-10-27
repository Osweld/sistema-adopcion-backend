package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ues.dsi.sistemaadopcionbackend.models.DTO.CreateSolicitudAdopcionDTO;
import ues.dsi.sistemaadopcionbackend.models.DTO.VerificarSolicitudDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;

import java.security.Principal;

public interface SolicitudAdopcionService {

    Page<SolicitudAdopcion> getAllSolicitudesAdopcion(Pageable pageable);
    Page<SolicitudAdopcion> getAllSolicitudesAdopcionByMascotaId(Long idMascota, Pageable pageable);
    Page<SolicitudAdopcion> getAllSolicitudesAdopcionByUsuarioId(Long idUsuario, Pageable pageable);
    Page<SolicitudAdopcion> getAllSolicitudesAdopcionByEstadoSolicitudAdopcion(Long idEstadoSolicitudAdopcion, Pageable pageable);
    SolicitudAdopcion getSolicitudByUsuarioAndEstadoSolicitud(Principal principal);
    SolicitudAdopcion getSolicitudAdopcionById(Long idSolicitudAdopcion);
    SolicitudAdopcion createSolicitudAdopcion(CreateSolicitudAdopcionDTO createSolicitudAdopcionDTO, Principal principal);
    SolicitudAdopcion verificarSolicitudAdopcion(VerificarSolicitudDTO verificarSolicitudDTO);
    SolicitudAdopcion deleteSolicitudAdopcionById(Long idSolicitudAdopcion);
    Page<SolicitudAdopcion> getSolicitudAdopcionByUsuarioAndEStadoAdopcion(Principal principal,Pageable pageable);

}