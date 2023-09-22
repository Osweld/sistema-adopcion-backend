package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.Foto;

import java.util.List;

public interface FotoRepository extends JpaRepository<Foto,Long> {

    List<Foto> findAllByMascotaId(Long mascotaId);
}
