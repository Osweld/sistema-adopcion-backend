package ues.dsi.sistemaadopcionbackend.services;

import java.util.List;

import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;

public interface MeGustaService {

    List<MeGusta> getAllMeGusta();
    List<MeGusta> getAllMeGustaByMascotaId(Long idMascota);
    List<MeGusta> getAllMeGustaByUsuarioId(Long idUsuario);
    MeGusta getMeGustaById(Long idMeGusta);
    MeGusta createMeGusta(MeGusta meGusta);
    MeGusta editMeGusta(Long idMeGusta, MeGusta meGusta);
    MeGusta deleteMeGustaById(Long idMeGusta);

}
