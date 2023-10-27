package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.dsi.sistemaadopcionbackend.models.DTO.MascotaIdDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.models.repository.MeGustaRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.UsuarioRepository;

@Service
public class MeGustaServiceImpl implements MeGustaService{

    private final MeGustaRepository meGustaRepository;

   
    public MeGustaServiceImpl(MeGustaRepository meGustaRepository) {
        this.meGustaRepository = meGustaRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<MeGusta> getAllMeGusta() {
        return meGustaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MeGusta> getAllMeGustaByMascotaId(Long idMascota) {
        if (idMascota == null)
            throw new IllegalArgumentException("El argumento idMascota no puede ser nulo");
        return meGustaRepository.findAllByMascotaId(idMascota);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MeGusta> getAllMeGustaByUsuarioId(Long idUsuario) {
        if (idUsuario == null)
            throw new IllegalArgumentException("El argumento idUsuario no puede ser nulo");
        return meGustaRepository.findAllByUsuarioId(idUsuario);
    }

    @Override
    public List<MascotaIdDTO> getMeGustaByIdUsuario(Principal principal) {
        return meGustaRepository.findMascotaIdsByUsuarioId(Long.parseLong(principal.getName()));
    }

    @Override
    @Transactional(readOnly = true)
    public MeGusta getMeGustaById(Long idMeGusta) {
        return meGustaRepository.findById(idMeGusta).orElseThrow(() -> new EntityNotFoundException("MeGusta no encontrado con el ID proporcionado: "+idMeGusta));
    }

    @Override
    @Transactional
    public MeGusta createAndDeleteMeGusta(Long idMascota, Principal principal) {
        Usuario usuario = new Usuario(Long.parseLong(principal.getName()));
        Mascota mascota = new Mascota(idMascota);
        MeGusta  meGusta = meGustaRepository.findMeGustaByUsuarioAndMascota(usuario,mascota).orElse(null);
        if(meGusta == null){
            meGusta = new MeGusta();
            meGusta.setMascota(mascota);
            meGusta.setUsuario(usuario);
            return meGustaRepository.save(meGusta);
        }else{
            meGustaRepository.delete(meGusta);
            return meGusta;
        }

    }


    @Override
    @Transactional
    public MeGusta editMeGusta(Long idMeGusta, MeGusta meGusta) {
        if(idMeGusta == null || meGusta == null){
            throw new IllegalArgumentException("Los argumentos idMeGusta y meGusta no pueden ser nulos");
        }
        MeGusta meGustaDB = meGustaRepository.findById(idMeGusta).orElseThrow(() -> new EntityNotFoundException("MeGusta no encontrado con el ID proporcionado: "+idMeGusta));

        if(meGusta.getMascota() != null) meGustaDB.setMascota(meGusta.getMascota());
        if(meGusta.getUsuario() != null) meGustaDB.setUsuario(meGusta.getUsuario());
       
        return meGustaRepository.save(meGustaDB);
    }

    @Override
    @Transactional
    public MeGusta deleteMeGustaById(Long idMeGusta) {
        if (idMeGusta == null) {
            throw new IllegalArgumentException("El argumento idMeGusta no puede ser nulo");
        }
        MeGusta meGusta = meGustaRepository.findById(idMeGusta).orElseThrow(() ->
                new EntityNotFoundException("MeGusta no encontrado con el ID proporcionado: " + idMeGusta));
        
        meGustaRepository.delete(meGusta);
        return meGusta;
    }

}
