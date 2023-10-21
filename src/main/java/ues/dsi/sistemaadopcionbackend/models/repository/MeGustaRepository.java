package ues.dsi.sistemaadopcionbackend.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;

public interface MeGustaRepository extends JpaRepository<MeGusta,Long> {

    List<MeGusta> findAllByMascotaId(Long idMascota);
    List<MeGusta> findAllByUsuarioId(Long idUsuario);
}
