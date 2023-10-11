package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ues.dsi.sistemaadopcionbackend.models.entity.Foto;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;

import java.io.IOException;
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
    List<Foto> saveMascotaPhotos(Long idMascota,MultipartFile[] multipartFiles) throws IOException;
    Mascota saveMascotaPhotoPerfil(Long idMascota,MultipartFile multipartFile) throws IOException;
    List<Foto> getMascotaPhotos(Long idMascota);

}
