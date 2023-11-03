package ues.dsi.sistemaadopcionbackend.services;

import java.security.Principal;
import java.util.List;

import ues.dsi.sistemaadopcionbackend.models.DTO.MascotaIdDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;

public interface MeGustaService {

    List<MeGusta> getAllMeGusta();
    List<MeGusta> getAllMeGustaByMascotaId(Long idMascota);
    List<MeGusta> getAllMeGustaByUsuarioId(Long idUsuario);
    List<MascotaIdDTO> getMeGustaByIdUsuario(Principal principal);
    MeGusta getMeGustaById(Long idMeGusta);
    MeGusta createAndDeleteMeGusta(Long idMascota, Principal principal);
    MeGusta editMeGusta(Long idMeGusta, MeGusta meGusta);
    MeGusta deleteMeGustaById(Long idMeGusta);

}
