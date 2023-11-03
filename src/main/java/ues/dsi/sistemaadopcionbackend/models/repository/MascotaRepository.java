package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ues.dsi.sistemaadopcionbackend.models.DTO.EstadoMascotaDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota,Long> {

    Page<Mascota> findAllByGeneroId(Long idGenero, Pageable pageable);
    Page<Mascota> findAllByRazaId(Long idRaza, Pageable pageable);
    Page<Mascota> findAllByEspecieId(Long idEspecie, Pageable pageable);
    Page<Mascota> findAllByEstadoMascotaId(Long idEstadoMascota, Pageable pageable);
    Page<Mascota> findMascotasByNombreIgnoreCase(String nombre,Pageable pageable);
    @Query("SELECT em.estado, COUNT(m) FROM Mascota m JOIN m.estadoMascota em GROUP BY em.estado")
    List<Object[]> countMascotasByEstadoMascota();


}
