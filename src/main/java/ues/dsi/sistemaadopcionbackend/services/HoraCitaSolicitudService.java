package ues.dsi.sistemaadopcionbackend.services;

import java.sql.Time;
import java.util.List;

import ues.dsi.sistemaadopcionbackend.models.entity.HoraCitaSolicitud;

public interface HoraCitaSolicitudService {

    List<HoraCitaSolicitud> getAllHorasCitaSolicitud();
    HoraCitaSolicitud getHoraCitaSolicitudByHoraCita(Time horaCita);
    HoraCitaSolicitud getHoraCitaSolicitudById(Long idHoraCitaSolicitud);
    HoraCitaSolicitud createHoraCitaSolicitud(HoraCitaSolicitud horaCitaSolicitud);
    HoraCitaSolicitud editHoraCitaSolicitud(Long idHoraCitaSolicitud, HoraCitaSolicitud horaCitaSolicitud);
    HoraCitaSolicitud deleteHoraCitaSolicitudById(Long idHoraCitaSolicitud);

}
