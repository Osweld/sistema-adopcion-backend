package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface SolicitudAdopcionRepository extends JpaRepository<SolicitudAdopcion,Long> {

    Page<SolicitudAdopcion> findAllByMascotaId(Long idMascota, Pageable pageable);
    Page<SolicitudAdopcion> findAllByUsuarioId(Long idUsuario, Pageable pageable);
    Page<SolicitudAdopcion> findAllByEstadoSolicitudAdopcionId(Long idEstadoSolicitudAdopcion, Pageable pageable);
    Page<SolicitudAdopcion> findAllByUsuarioAndEstadoSolicitudAdopcion(Usuario usuario,EstadoSolicitudAdopcion estadoSolicitudAdopcion, Pageable pageable);
    Optional<SolicitudAdopcion> getSolicitudAdopcionByUsuarioAndEstadoSolicitudAdopcion(Usuario usuario,EstadoSolicitudAdopcion estadoSolicitudAdopcion);
    Boolean existsSolicitudAdopcionByUsuarioAndEstadoSolicitudAdopcion(Usuario usuario, EstadoSolicitudAdopcion estadoSolicitudAdopcion);
    Boolean existsSolicitudAdopcionByMascotaId(Long idMascota);
    List<SolicitudAdopcion> findAllByMascotaId(Long idMascota);
    //List<SolicitudAdopcion> getSolicitudAdopcionByEstadoSolicitudAdopcion

}
