package ues.dsi.sistemaadopcionbackend.services;

import java.sql.Time;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ues.dsi.sistemaadopcionbackend.models.entity.CitaSolicitudAdopcion;

public interface CitaSolicitudAdopcionService {

    Page<CitaSolicitudAdopcion> getAllCitasSolicitudAdopcion(Pageable pageable);
    Page<CitaSolicitudAdopcion> getAllByFechaCita(Date fechaCita, Pageable pageable);
    Page<CitaSolicitudAdopcion> getAllByEstadoCitaSolicitudId(Long idEstadoCitaSolicitud, Pageable pageable);
    //CitaSolicitudAdopcion getByIdCitaSolicitudAdopcionAndIdHoraCitaSolicitud(Long idCitaSolicitudAdopcion, Long idHoraCitaSolicitud);
    //Boolean existCitaSolicitudAdopcionByFechaCita(Date fechaCita);
    //Boolean existCitaSolicitudAdopcionByIdHoraCitaSolicitud(Long idHoraCitaSolicitud);
    CitaSolicitudAdopcion getCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion);
    CitaSolicitudAdopcion createCitaSolicitudAdopcion(CitaSolicitudAdopcion citaSolicitudAdopcion);
    CitaSolicitudAdopcion editCitaSolicitudAdopcion(Long idCitaSolicitudAdopcion, CitaSolicitudAdopcion citaSolicitudAdopcion);
    CitaSolicitudAdopcion deleteCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion);

}