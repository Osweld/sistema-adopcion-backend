package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;

import java.sql.Time;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoCitaSolicitud;
import ues.dsi.sistemaadopcionbackend.models.entity.CitaSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.repository.EstadoCitaSolicitudRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.CitaSolicitudAdopcionRepository;

@Service
public class CitaSolicitudAdopcionServiceImpl implements CitaSolicitudAdopcionService{

    private final CitaSolicitudAdopcionRepository citaSolicitudAdopcionRepository;
    private final EstadoCitaSolicitudRepository estadoCitaSolicitudAdopcionRepository;
   
    public CitaSolicitudAdopcionServiceImpl(CitaSolicitudAdopcionRepository citaSolicitudAdopcionRepository, EstadoCitaSolicitudRepository estadoCitaSolicitudAdopcionRepository) {
        this.citaSolicitudAdopcionRepository = citaSolicitudAdopcionRepository;
        this.estadoCitaSolicitudAdopcionRepository = estadoCitaSolicitudAdopcionRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<CitaSolicitudAdopcion> getAllCitasSolicitudAdopcion(Pageable pageable) {
        return citaSolicitudAdopcionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CitaSolicitudAdopcion> getAllByFechaCita(Date fechaCita, Pageable pageable) {
        if (fechaCita == null)
            throw new IllegalArgumentException("El argumento fechaCita no puede ser nulo");
        return citaSolicitudAdopcionRepository.findAllByFechaCita(fechaCita, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CitaSolicitudAdopcion> getAllByEstadoCitaSolicitudId(Long idEstadoCitaSolicitud, Pageable pageable) {
        if (idEstadoCitaSolicitud == null)
            throw new IllegalArgumentException("El argumento idEstadoCitaSolicitud no puede ser nulo");
        return citaSolicitudAdopcionRepository.findAllByEstadoCitaSolicitudId(idEstadoCitaSolicitud, pageable);
    }
/* 
    @Override
    @Transactional(readOnly = true)
    public CitaSolicitudAdopcion getByIdCitaSolicitudAdopcionAndIdHoraCitaSolicitud(Long idCitaSolicitudAdopcion, Long idHoraCitaSolicitud) {
        if (idCitaSolicitudAdopcion == null)
            throw new IllegalArgumentException("El argumento idCitaSolicitudAdopcion no puede ser nulo");
        if (idHoraCitaSolicitud == null)
            throw new IllegalArgumentException("El argumento horaCita no puede ser nulo");
        
        CitaSolicitudAdopcion citaSolicitud = citaSolicitudAdopcionRepository.findByIdCitaSolicitudAdopcionAndIdHoraCitaSolicitud(idCitaSolicitudAdopcion, idHoraCitaSolicitud);

        if (citaSolicitud == null)
            throw new EntityNotFoundException("Cita Solicitud Adopcion no encontrada con el ID y hora de cita proporcionado: "+idCitaSolicitudAdopcion+", "+idHoraCitaSolicitud);
        
            return citaSolicitud;
    }
*/
    @Override
    @Transactional(readOnly = true)
    public CitaSolicitudAdopcion getCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion) {
        return citaSolicitudAdopcionRepository.findById(idCitaSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("Cita Solicitud Adopcion no encontrada con el ID proporcionado: "+idCitaSolicitudAdopcion));
    }

    @Override
    @Transactional
    public CitaSolicitudAdopcion createCitaSolicitudAdopcion(CitaSolicitudAdopcion citaSolicitudAdopcion) {
        return citaSolicitudAdopcionRepository.save(citaSolicitudAdopcion);
    }

    @Override
    @Transactional
    public CitaSolicitudAdopcion editCitaSolicitudAdopcion(Long idCitaSolicitudAdopcion, CitaSolicitudAdopcion citaSolicitudAdopcion) {
        if(idCitaSolicitudAdopcion == null || citaSolicitudAdopcion == null){
            throw new IllegalArgumentException("Los argumentos idCitaSolicitudAdopcion y citaSolicitudAdopcion no pueden ser nulos");
        }
        CitaSolicitudAdopcion citaSolicitudAdopcionDB = citaSolicitudAdopcionRepository.findById(idCitaSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("Cita Solicitud Adopcion no encontrada con el ID proporcionado: "+idCitaSolicitudAdopcion));

        if(citaSolicitudAdopcion.getMotivoCita() != null) citaSolicitudAdopcionDB.setMotivoCita(citaSolicitudAdopcion.getMotivoCita());
        if(citaSolicitudAdopcion.getDescripcion() != null) citaSolicitudAdopcionDB.setDescripcion(citaSolicitudAdopcion.getDescripcion());
        if(citaSolicitudAdopcion.getFechaCita() != null) citaSolicitudAdopcionDB.setFechaCita(citaSolicitudAdopcion.getFechaCita());
        if(citaSolicitudAdopcion.getHoraCitaSolicitud() != null) citaSolicitudAdopcionDB.setHoraCitaSolicitud(citaSolicitudAdopcion.getHoraCitaSolicitud());
        if(citaSolicitudAdopcion.getEstadoCitaSolicitud() != null) citaSolicitudAdopcionDB.setEstadoCitaSolicitud(citaSolicitudAdopcion.getEstadoCitaSolicitud());
        if(citaSolicitudAdopcion.getSolicitudAdopcion() != null) citaSolicitudAdopcionDB.setSolicitudAdopcion(citaSolicitudAdopcion.getSolicitudAdopcion());
        
        return citaSolicitudAdopcionRepository.save(citaSolicitudAdopcionDB);
    }

    @Override
    @Transactional
    public CitaSolicitudAdopcion deleteCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion) {
        if (idCitaSolicitudAdopcion == null) {
            throw new IllegalArgumentException("El argumento idCitaSolicitudAdopcion no puede ser nulo");
        }
        CitaSolicitudAdopcion citaSolicitudAdopcion = citaSolicitudAdopcionRepository.findById(idCitaSolicitudAdopcion).orElseThrow(() ->
                new EntityNotFoundException("Cita Solicitud Adopción no encontrada con el ID proporcionado: " + idCitaSolicitudAdopcion));

        EstadoCitaSolicitud estadoCitaSolicitudAdopcion = estadoCitaSolicitudAdopcionRepository.findByEstado("BORRADA");
        if(estadoCitaSolicitudAdopcion == null)
            throw new IllegalArgumentException("Estado Solicitud Adopción no encontrado con el Estado proporcionado: BORRADA");
        
        citaSolicitudAdopcion.setEstadoCitaSolicitud(estadoCitaSolicitudAdopcion);
        
        return citaSolicitudAdopcionRepository.save(citaSolicitudAdopcion);
    }

    /*@Override
    public Boolean existCitaSolicitudAdopcionByFechaCita(Date fechaCita) {
        if (fechaCita == null)
            throw new IllegalArgumentException("El argumento fechaCita no puede ser nulo");
        return citaSolicitudAdopcionRepository.existCitaSolicitudAdopcionByFechaCita(fechaCita);
    }

    @Override
    public Boolean existCitaSolicitudAdopcionByIdHoraCitaSolicitud(Long idHoraCitaSolicitud) {
      if (idHoraCitaSolicitud == null)
            throw new IllegalArgumentException("El argumento idHoraCitaSolicitud no puede ser nulo");
        return citaSolicitudAdopcionRepository.existCitaSolicitudAdopcionByIdHoraCitaSolicitud(idHoraCitaSolicitud);
    }
*/
}
