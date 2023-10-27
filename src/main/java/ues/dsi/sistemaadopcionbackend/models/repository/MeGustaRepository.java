package ues.dsi.sistemaadopcionbackend.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ues.dsi.sistemaadopcionbackend.models.DTO.MascotaIdDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;

public interface MeGustaRepository extends JpaRepository<MeGusta,Long> {

    List<MeGusta> findAllByMascotaId(Long idMascota);
    List<MeGusta> findAllByUsuarioId(Long idUsuario);
    Optional<MeGusta> findMeGustaByUsuarioAndMascota(Usuario usuario, Mascota mascota);

    @Query("SELECT new ues.dsi.sistemaadopcionbackend.models.DTO.MascotaIdDTO(m.mascota.id) FROM MeGusta m WHERE m.usuario.id = :usuarioId")
    List<MascotaIdDTO> findMascotaIdsByUsuarioId(@Param("usuarioId") Long usuarioId);
}
