package ues.dsi.sistemaadopcionbackend.models.repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ues.dsi.sistemaadopcionbackend.models.entity.CitaSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;

public interface CitaSolicitudAdopcionRepository extends JpaRepository<CitaSolicitudAdopcion,Long> {

    List<CitaSolicitudAdopcion> findAllByFechaCita(Date fechaCita);
    List<CitaSolicitudAdopcion> findAllByEstadoCitaSolicitudId(Long idEstadoCitaSolicitud);
    List<CitaSolicitudAdopcion> findBySolicitudAdopcionId(Long idSolicitudAdopcion);
    Page<CitaSolicitudAdopcion> getCitaSolicitudAdopcionBySolicitudAdopcion_Usuario(Usuario usuario, Pageable pageable);
    Boolean existsCitaSolicitudAdopcionByFechaCitaAndHoraCitaSolicitudId(Date fechaCita, Long idHoraCitaSolicitud);
    Boolean existsCitaSolicitudAdopcionByFechaCita(Date fechaCita);
    Boolean existsCitaSolicitudAdopcionByHoraCitaSolicitudId(Long idHoraCitaSolicitud);
}
