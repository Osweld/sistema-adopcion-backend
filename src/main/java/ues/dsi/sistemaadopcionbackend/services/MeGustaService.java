package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;

public interface MeGustaService {

    Page<MeGusta> getAllMeGusta(Pageable pageable);
    Page<MeGusta> getAllMeGustaByMascotaId(Long idMascota, Pageable pageable);
    Page<MeGusta> getAllMeGustaByUsuarioId(Long idUsuario, Pageable pageable);
    MeGusta getMeGustaById(Long idMeGusta);
    MeGusta createMeGusta(MeGusta meGusta);
    MeGusta editMeGusta(Long idMeGusta, MeGusta meGusta);
    MeGusta deleteMeGustaById(Long idMeGusta);

}
