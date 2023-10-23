package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSolicitudAdopcion;

public interface EstadoSolicitudAdopcionRepository extends JpaRepository<EstadoSolicitudAdopcion,Long> {

    EstadoSolicitudAdopcion findByEstado(String estado);
}
