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
            throw new IllegalArgumentException("El argumento idMascota no puede ser nulo");
        return citaSolicitudAdopcionRepository.findAllByFechaCita(fechaCita, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CitaSolicitudAdopcion> getAllByEstadoCitaSolicitudId(Long idEstadoCitaSolicitud, Pageable pageable) {
        if (idEstadoCitaSolicitud == null)
            throw new IllegalArgumentException("El argumento idEstadoCitaSolicitud no puede ser nulo");
        return citaSolicitudAdopcionRepository.findAllByEstadoCitaSolicitudId(idEstadoCitaSolicitud, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public CitaSolicitudAdopcion getByIdCitaSolicitudAdopcionAndHoraCita(Long idCitaSolicitudAdopcion, Time horaCita) {
        if (idCitaSolicitudAdopcion == null)
            throw new IllegalArgumentException("El argumento idCitaSolicitudAdopcion no puede ser nulo");
        if (horaCita == null)
            throw new IllegalArgumentException("El argumento horaCita no puede ser nulo");
        
        CitaSolicitudAdopcion citaSolicitud = citaSolicitudAdopcionRepository.findByIdCitaSolicitudAdopcionAndHoraCita(idCitaSolicitudAdopcion, horaCita);

        if (citaSolicitud == null)
            throw new EntityNotFoundException("Cita Solicitud Adopcion no encontrada con el ID y hora de cita proporcionado: "+idCitaSolicitudAdopcion+", "+horaCita);
        
            return citaSolicitud;
    }

    @Override
    @Transactional(readOnly = true)
    public CitaSolicitudAdopcion getCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion) {
        return citaSolicitudAdopcionRepository.findById(idCitaSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("Cita Solicitud Adopcion no encontrada con el ID proporcionado: "+idCitaSolicitudAdopcion));
    }

    @Override
    @Transactional
    public CitaSolicitudAdopcion createCitaSolicitudAdopcion(CitaSolicitudAdopcion solicitudAdopcion) {
        return citaSolicitudAdopcionRepository.save(solicitudAdopcion);
    }

    @Override
    @Transactional
    public CitaSolicitudAdopcion editCitaSolicitudAdopcion(Long idCitaSolicitudAdopcion, CitaSolicitudAdopcion solicitudAdopcion) {
        if(idCitaSolicitudAdopcion == null || solicitudAdopcion == null){
            throw new IllegalArgumentException("Los argumentos idCitaSolicitudAdopcion y solicitudAdopcion no pueden ser nulos");
        }
        CitaSolicitudAdopcion solicitudAdopcionDB = citaSolicitudAdopcionRepository.findById(idCitaSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("Cita Solicitud Adopcion no encontrada con el ID proporcionado: "+idCitaSolicitudAdopcion));

        solicitudAdopcionDB.setMotivo(solicitudAdopcion.getMotivo());
        if(solicitudAdopcion.getDescripcion() != null) solicitudAdopcionDB.setDescripcion(solicitudAdopcion.getDescripcion());
        if(solicitudAdopcion.getMascota() != null) solicitudAdopcionDB.setMascota(solicitudAdopcion.getMascota());
        if(solicitudAdopcion.getUsuario() != null) solicitudAdopcionDB.setUsuario(solicitudAdopcion.getUsuario());
        if(solicitudAdopcion.getEstadoCitaSolicitud() != null) solicitudAdopcionDB.setEstadoCitaSolicitud(solicitudAdopcion.getEstadoCitaSolicitud());
        if(solicitudAdopcion.getDetalleCitaSolicitudAdopcion() != null) solicitudAdopcionDB.setDetalleCitaSolicitudAdopcion(solicitudAdopcion.getDetalleCitaSolicitudAdopcion());

        return citaSolicitudAdopcionRepository.save(solicitudAdopcionDB);
    }

    @Override
    @Transactional
    public CitaSolicitudAdopcion deleteCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion) {
        if (idCitaSolicitudAdopcion == null) {
            throw new IllegalArgumentException("El argumento idCitaSolicitudAdopcion no puede ser nulo");
        }
        CitaSolicitudAdopcion solicitudAdopcion = citaSolicitudAdopcionRepository.findById(idCitaSolicitudAdopcion).orElseThrow(() ->
                new EntityNotFoundException("Solicitud Adopción no encontrada con el ID proporcionado: " + idCitaSolicitudAdopcion));

        EstadoCitaSolicitud estadoCitaSolicitudAdopcion = estadoCitaSolicitudAdopcionRepository.findByEstado("BORRADA");
        if(estadoCitaSolicitudAdopcion == null)
            throw new IllegalArgumentException("Estado Solicitud Adopción no encontrado con el Estado proporcionado: BORRADA");
        
        solicitudAdopcion.setEstadoCitaSolicitud(estadoCitaSolicitudAdopcion);
        
        return citaSolicitudAdopcionRepository.save(solicitudAdopcion);
    }

}
