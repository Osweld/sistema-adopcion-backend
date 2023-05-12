package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;

import java.util.List;

public interface MascotaService {

    Page<Mascota> getAllMascotas(Pageable pageable);
    Page<Mascota> getAllMascotasByGenero(Long idGenero,Pageable pageable);
    Page<Mascota> getAllMascotasByRaza(Long idRaza,Pageable pageable);
    Page<Mascota> getAllMascotasByEspecie(Long idEspecie,Pageable pageable);
    Mascota getMascotaById(Long idMascota);
    Mascota createMascota(Mascota mascota);
    Mascota editMascota(Long idMascota,Mascota mascota);
    Mascota deleteMascotaById(Long idMascota);
    
}
