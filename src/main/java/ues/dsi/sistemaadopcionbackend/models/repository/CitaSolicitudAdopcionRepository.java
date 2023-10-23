package ues.dsi.sistemaadopcionbackend.models.repository;

import java.sql.Time;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ues.dsi.sistemaadopcionbackend.models.entity.CitaSolicitudAdopcion;

public interface CitaSolicitudAdopcionRepository extends JpaRepository<CitaSolicitudAdopcion,Long> {

    Page<CitaSolicitudAdopcion> findAllByFechaCita(Date fechaCita, Pageable pageable);
    Page<CitaSolicitudAdopcion> findAllByEstadoCitaSolicitudId(Long idEstadoCitaSolicitud, Pageable pageable);
    //CitaSolicitudAdopcion findByIdCitaSolicitudAdopcionAndIdHoraCitaSolicitud(Long idCitaSolicitudAdopcion, Long idHoraCitaSolicitud);
    //Boolean existCitaSolicitudAdopcionByFechaCita(Date fechaCita);
    //Boolean existCitaSolicitudAdopcionByIdHoraCitaSolicitud(Long idHoraCitaSolicitud);
}
