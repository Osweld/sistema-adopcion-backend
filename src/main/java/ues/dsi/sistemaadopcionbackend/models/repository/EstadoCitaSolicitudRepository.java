package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoCitaSolicitud;

public interface EstadoCitaSolicitudRepository extends JpaRepository<EstadoCitaSolicitud,Long> {

    EstadoCitaSolicitud findByEstado(String estado);
}
