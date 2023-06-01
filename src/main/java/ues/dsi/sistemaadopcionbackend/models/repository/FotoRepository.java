package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.Foto;

public interface FotoRepository extends JpaRepository<Foto,Long> {
}
