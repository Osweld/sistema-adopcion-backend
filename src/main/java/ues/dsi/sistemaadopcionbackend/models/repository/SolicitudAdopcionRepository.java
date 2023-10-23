package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;

public interface SolicitudAdopcionRepository extends JpaRepository<SolicitudAdopcion,Long> {

    Page<SolicitudAdopcion> findAllByMascotaId(Long idMascota, Pageable pageable);
    Page<SolicitudAdopcion> findAllByUsuarioId(Long idUsuario, Pageable pageable);
    Page<SolicitudAdopcion> findAllByEstadoSolicitudAdopcionId(Long idEstadoSolicitudAdopcion, Pageable pageable);
}
