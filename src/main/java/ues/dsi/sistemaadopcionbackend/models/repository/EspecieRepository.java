package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.Especie;

public interface EspecieRepository extends JpaRepository<Especie,Long> {
}
