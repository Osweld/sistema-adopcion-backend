package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;
import ues.dsi.sistemaadopcionbackend.models.repository.MeGustaRepository;

@Service
public class MeGustaServiceImpl implements MeGustaService{

    private final MeGustaRepository meGustaRepository;
   
    public MeGustaServiceImpl(MeGustaRepository meGustaRepository) {
        this.meGustaRepository = meGustaRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<MeGusta> getAllMeGusta(Pageable pageable) {
        return meGustaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MeGusta> getAllMeGustaByMascotaId(Long idMascota, Pageable pageable) {
        if (idMascota == null)
            throw new IllegalArgumentException("El argumento idMascota no puede ser nulo");
        return meGustaRepository.findAllByMascotaId(idMascota, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MeGusta> getAllMeGustaByUsuarioId(Long idUsuario, Pageable pageable) {
        if (idUsuario == null)
            throw new IllegalArgumentException("El argumento idUsuario no puede ser nulo");
        return meGustaRepository.findAllByUsuarioId(idUsuario, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public MeGusta getMeGustaById(Long idMeGusta) {
        return meGustaRepository.findById(idMeGusta).orElseThrow(() -> new EntityNotFoundException("MeGusta no encontrado con el ID proporcionado: "+idMeGusta));
    }

    @Override
    @Transactional
    public MeGusta createMeGusta(MeGusta meGusta) {
        return meGustaRepository.save(meGusta);
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
