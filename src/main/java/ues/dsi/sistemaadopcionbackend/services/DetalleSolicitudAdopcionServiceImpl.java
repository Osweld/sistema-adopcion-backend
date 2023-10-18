package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.dsi.sistemaadopcionbackend.models.entity.DetalleSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.repository.DetalleSolicitudAdopcionRepository;

@Service
public class DetalleSolicitudAdopcionServiceImpl implements DetalleSolicitudAdopcionService{

    private final DetalleSolicitudAdopcionRepository detalleSolicitudAdopcionRepository;
   
    public DetalleSolicitudAdopcionServiceImpl(DetalleSolicitudAdopcionRepository detalleSolicitudAdopcionRepository) {
        this.detalleSolicitudAdopcionRepository = detalleSolicitudAdopcionRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<DetalleSolicitudAdopcion> getAllDetalleSolicitudesAdopcion(Pageable pageable) {
        return detalleSolicitudAdopcionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleSolicitudAdopcion getDetalleSolicitudAdopcionById(Long idDetalleSolicitudAdopcion) {
        return detalleSolicitudAdopcionRepository.findById(idDetalleSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("DetalleSolicitud Adopcion no encontrado con el ID proporcionado: "+idDetalleSolicitudAdopcion));
    }

    @Override
    @Transactional
    public DetalleSolicitudAdopcion createDetalleSolicitudAdopcion(DetalleSolicitudAdopcion detalleSolicitudAdopcion) {
        return detalleSolicitudAdopcionRepository.save(detalleSolicitudAdopcion);
    }

    @Override
    @Transactional
    public DetalleSolicitudAdopcion editDetalleSolicitudAdopcion(Long idDetalleSolicitudAdopcion, DetalleSolicitudAdopcion detalleSolicitudAdopcion) {
        if(idDetalleSolicitudAdopcion == null || detalleSolicitudAdopcion == null){
            throw new IllegalArgumentException("Los argumentos idDetalleSolicitudAdopcion y detalleSolicitudAdopcion no pueden ser nulos");
        }
        DetalleSolicitudAdopcion detalleSolicitudAdopcionDB = detalleSolicitudAdopcionRepository.findById(idDetalleSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("DetalleSolicitud Adopcion no encontrado con el ID proporcionado: "+idDetalleSolicitudAdopcion));

        if(detalleSolicitudAdopcion.getNumIntegrantesHogar() != null) detalleSolicitudAdopcionDB.setNumIntegrantesHogar(detalleSolicitudAdopcion.getNumIntegrantesHogar());
        if(detalleSolicitudAdopcion.getCasaPropia() != null) detalleSolicitudAdopcionDB.setCasaPropia(detalleSolicitudAdopcion.getCasaPropia());
        if(detalleSolicitudAdopcion.getZonaVivienda() != null) detalleSolicitudAdopcionDB.setZonaVivienda(detalleSolicitudAdopcion.getZonaVivienda());
        if(detalleSolicitudAdopcion.getPoseePatio() != null) detalleSolicitudAdopcionDB.setPoseePatio(detalleSolicitudAdopcion.getPoseePatio());
        if(detalleSolicitudAdopcion.getTieneNinos() != null) detalleSolicitudAdopcionDB.setTieneNinos(detalleSolicitudAdopcion.getTieneNinos());
        if(detalleSolicitudAdopcion.getTieneMascotas() != null) detalleSolicitudAdopcionDB.setTieneMascotas(detalleSolicitudAdopcion.getTieneMascotas());
        if(detalleSolicitudAdopcion.getDetalleMascotasPosee() != null) detalleSolicitudAdopcionDB.setDetalleMascotasPosee(detalleSolicitudAdopcion.getDetalleMascotasPosee());
        if(detalleSolicitudAdopcion.getTuvoMascotas() != null) detalleSolicitudAdopcionDB.setTuvoMascotas(detalleSolicitudAdopcion.getTuvoMascotas());
        if(detalleSolicitudAdopcion.getDetalleMascotasTuvo() != null) detalleSolicitudAdopcionDB.setDetalleMascotasTuvo(detalleSolicitudAdopcion.getDetalleMascotasTuvo());
        if(detalleSolicitudAdopcion.getDisponeTiempoAlDia() != null) detalleSolicitudAdopcionDB.setDisponeTiempoAlDia(detalleSolicitudAdopcion.getDisponeTiempoAlDia());
        
        return detalleSolicitudAdopcionRepository.save(detalleSolicitudAdopcionDB);
    }

    @Override
    @Transactional
    public DetalleSolicitudAdopcion deleteDetalleSolicitudAdopcionById(Long idDetalleSolicitudAdopcion) {
        if (idDetalleSolicitudAdopcion == null) {
            throw new IllegalArgumentException("El argumento idDetalleSolicitudAdopcion no puede ser nulo");
        }
        DetalleSolicitudAdopcion detalleSolicitudAdopcion = detalleSolicitudAdopcionRepository.findById(idDetalleSolicitudAdopcion).orElseThrow(() ->
                new EntityNotFoundException("DetalleSolicitud Adopción no encontrada con el ID proporcionado: " + idDetalleSolicitudAdopcion));

        detalleSolicitudAdopcionRepository.delete(detalleSolicitudAdopcion);

        return detalleSolicitudAdopcion;
    }

}
