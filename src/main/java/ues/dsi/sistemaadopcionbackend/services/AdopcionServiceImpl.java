package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.DTO.GenerateAdopcionDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Adopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoMascota;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.models.repository.AdopcionRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.MascotaRepository;

@Service
public class AdopcionServiceImpl implements AdopcionService{

    private final AdopcionRepository adopcionRepository;
    private final MascotaRepository mascotaRepository;

    public AdopcionServiceImpl(AdopcionRepository adopcionRepository, MascotaRepository mascotaRepository) {
        this.adopcionRepository = adopcionRepository;
        this.mascotaRepository = mascotaRepository;
    }


    @Override
    @Transactional
    public Adopcion createAdopcion(GenerateAdopcionDTO generateAdopcionDTO) {
        Usuario usuario = new Usuario(generateAdopcionDTO.getIdUsuario());
        Mascota mascota = mascotaRepository.findById(generateAdopcionDTO.getIdMascota()).orElseThrow();
        if(mascota.getEstadoMascota().getId() == 3L){
            throw new IllegalArgumentException("No se puede adoptar una mascota que ya este adoptada");
        }
        mascota.setEstadoMascota(new EstadoMascota(3L));
        mascotaRepository.save(mascota);
        Adopcion adopcion = new Adopcion();
        adopcion.setUsuario(usuario);
        adopcion.setMascota(mascota);
        return adopcionRepository.save(adopcion);
    }

    @Override
    @Transactional
    public Adopcion deleteAdopcion(Long idAdopcion) {
        Adopcion adopcion = adopcionRepository.findById(idAdopcion).orElseThrow();
        Mascota mascota = adopcion.getMascota();
        mascota.setEstadoMascota(new EstadoMascota(1L));
        mascotaRepository.save(mascota);
        adopcionRepository.delete(adopcion);
        return adopcion;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Adopcion> getAllAdopciones(Pageable pageable) {
        return adopcionRepository.findAll(pageable);
    }
}
