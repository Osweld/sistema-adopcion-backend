package ues.dsi.sistemaadopcionbackend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ues.dsi.sistemaadopcionbackend.models.entity.DetalleSolicitudAdopcion;

public interface DetalleSolicitudAdopcionService {

    Page<DetalleSolicitudAdopcion> getAllDetalleSolicitudesAdopcion(Pageable pageable);
    DetalleSolicitudAdopcion getDetalleSolicitudAdopcionById(Long idDetalleSolicitudAdopcion);
    DetalleSolicitudAdopcion createDetalleSolicitudAdopcion(DetalleSolicitudAdopcion detalleSolicitudAdopcion);
    DetalleSolicitudAdopcion editDetalleSolicitudAdopcion(Long idDetalleSolicitudAdopcion, DetalleSolicitudAdopcion detalleSolicitudAdopcion);
    DetalleSolicitudAdopcion deleteDetalleSolicitudAdopcionById(Long idDetalleSolicitudAdopcion);

}
