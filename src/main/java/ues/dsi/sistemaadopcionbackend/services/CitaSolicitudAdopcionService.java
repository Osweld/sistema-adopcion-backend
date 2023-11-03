package ues.dsi.sistemaadopcionbackend.services;

import java.security.Principal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ues.dsi.sistemaadopcionbackend.models.entity.CitaSolicitudAdopcion;

public interface CitaSolicitudAdopcionService {

    Page<CitaSolicitudAdopcion> getAllCitasSolicitudAdopcion(Pageable pageable);
    List<CitaSolicitudAdopcion> getAllByFechaCita(Date fechaCita);
    List<CitaSolicitudAdopcion> getAllByEstadoCitaSolicitudId(Long idEstadoCitaSolicitud);
    List<CitaSolicitudAdopcion> getBySolicitudAdopcionId(Long idSolicitudAdopcion);
    Page<CitaSolicitudAdopcion> getByUsuario(Principal principal, Pageable pageable);
    Boolean getExistsCitaSolicitudAdopcionByFechaCitaAndHoraCitaSolicitudId(String fechaCita, Long idHoraCitaSolicitud);
    Boolean getExistsCitaSolicitudAdopcionByFechaCita(String fechaCita);
    Boolean getExistsCitaSolicitudAdopcionByHoraCitaSolicitudId(Long idHoraCitaSolicitud);
    CitaSolicitudAdopcion getCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion);
    CitaSolicitudAdopcion createCitaSolicitudAdopcion(CitaSolicitudAdopcion citaSolicitudAdopcion);
    CitaSolicitudAdopcion editCitaSolicitudAdopcion(Long idCitaSolicitudAdopcion, CitaSolicitudAdopcion citaSolicitudAdopcion);
    CitaSolicitudAdopcion deleteCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion);

}
