package ues.dsi.sistemaadopcionbackend.models.repository;

import java.sql.Time;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.dsi.sistemaadopcionbackend.models.entity.HoraCitaSolicitud;

public interface HoraCitaSolicitudRepository extends JpaRepository<HoraCitaSolicitud,Long> {

    HoraCitaSolicitud findByHoraCita(Time horaCita);
}
