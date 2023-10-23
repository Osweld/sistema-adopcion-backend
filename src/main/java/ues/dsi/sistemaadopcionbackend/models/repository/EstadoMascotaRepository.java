package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoMascota;

public interface EstadoMascotaRepository extends JpaRepository<EstadoMascota,Long> {

    EstadoMascota findByEstado(String estado);
}
