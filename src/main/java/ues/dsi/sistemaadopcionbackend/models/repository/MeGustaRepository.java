package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;

public interface MeGustaRepository extends JpaRepository<MeGusta,Long> {

    Page<MeGusta> findAllByMascotaId(Long idMascota, Pageable pageable);
    Page<MeGusta> findAllByUsuarioId(Long idUsuario, Pageable pageable);
}
