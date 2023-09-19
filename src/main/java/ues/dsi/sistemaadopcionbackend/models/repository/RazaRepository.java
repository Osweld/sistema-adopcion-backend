package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.Raza;

import java.util.List;

public interface RazaRepository extends JpaRepository<Raza, Long> {

    public List<Raza> findAllByEspecieId(Long id);
}
