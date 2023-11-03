package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.Adopcion;

public interface AdopcionRepository  extends JpaRepository<Adopcion, Long> {
}
