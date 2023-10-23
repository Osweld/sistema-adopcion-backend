package ues.dsi.sistemaadopcionbackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota,Long> {

    Page<Mascota> findAllByGeneroId(Long idGenero, Pageable pageable);
    Page<Mascota> findAllByRazaId(Long idRaza, Pageable pageable);
    Page<Mascota> findAllByEspecieId(Long idEspecie, Pageable pageable);
    Page<Mascota> findAllByEstadoMascotaId(Long idEstadoMascota, Pageable pageable);
    Page<Mascota> findMascotasByNombreIgnoreCase(String nombre,Pageable pageable);

}
