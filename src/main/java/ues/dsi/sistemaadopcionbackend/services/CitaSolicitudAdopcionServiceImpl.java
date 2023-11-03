package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.dsi.sistemaadopcionbackend.exceptions.UniqueValidationException;
import ues.dsi.sistemaadopcionbackend.models.entity.CitaSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.repository.CitaSolicitudAdopcionRepository;

@Service
public class CitaSolicitudAdopcionServiceImpl implements CitaSolicitudAdopcionService{

    private final CitaSolicitudAdopcionRepository citaSolicitudAdopcionRepository;
   
    public CitaSolicitudAdopcionServiceImpl(CitaSolicitudAdopcionRepository citaSolicitudAdopcionRepository) {
        this.citaSolicitudAdopcionRepository = citaSolicitudAdopcionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CitaSolicitudAdopcion> getAllCitasSolicitudAdopcion(Pageable pageable) {
        return citaSolicitudAdopcionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaSolicitudAdopcion> getAllByFechaCita(Date fechaCita) {
        if (fechaCita == null)
            throw new IllegalArgumentException("El argumento fechaCita no puede ser nulo");
        return citaSolicitudAdopcionRepository.findAllByFechaCita(fechaCita);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaSolicitudAdopcion> getAllByEstadoCitaSolicitudId(Long idEstadoCitaSolicitud) {
        if (idEstadoCitaSolicitud == null)
            throw new IllegalArgumentException("El argumento idEstadoCitaSolicitud no puede ser nulo");
        return citaSolicitudAdopcionRepository.findAllByEstadoCitaSolicitudId(idEstadoCitaSolicitud);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaSolicitudAdopcion> getBySolicitudAdopcionId(Long idSolicitudAdopcion) {
        if (idSolicitudAdopcion == null)
            throw new IllegalArgumentException("El argumento idSolicitudAdopcion no puede ser nulo");
        return citaSolicitudAdopcionRepository.findBySolicitudAdopcionId(idSolicitudAdopcion);
    }

    @Override
    @Transactional(readOnly = true)
    public CitaSolicitudAdopcion getCitaSolicitudAdopcionById(Long idCitaSolicitudAdopcion) {
        return citaSolicitudAdopcionRepository.findById(idCitaSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("Cita Solicitud Adopcion no encontrada con el ID proporcionado: "+idCitaSolicitudAdopcion));
    }

    @Override
    @Transactional
    public CitaSolicitudAdopcion createCitaSolicitudAdopcion(CitaSolicitudAdopcion citaSolicitudAdopcion) {
        
        Date fechaCita = citaSolicitudAdopcion.getFechaCita();
        Long idHoraCita = citaSolicitudAdopcion.getHoraCitaSolicitud().getId();
        
        if (fechaCita == null)
            throw new IllegalArgumentException("El argumento fechaCita no puede ser nulo");

         if (idHoraCita == null)
            throw new IllegalArgumentException("El argumento idHoraCita no puede ser nulo");
        
        Boolean existsCita = citaSolicitudAdopcionRepository.existsCitaSolicitudAdopcionByFechaCitaAndHoraCitaSolicitudId(fechaCita, idHoraCita);
        
        if(existsCita)
            throw new UniqueValidationException("Ya existe la cita de solicitud con la fecha y Hora de cita ingresados");
        
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

        if (citaSolicitudAdopcion.getFechaCita() != null || citaSolicitudAdopcion.getHoraCitaSolicitud() != null){
            Date fechaCita = citaSolicitudAdopcion.getFechaCita();
            Long idHoraCita = citaSolicitudAdopcion.getHoraCitaSolicitud().getId();
            Boolean existsCita = citaSolicitudAdopcionRepository.existsCitaSolicitudAdopcionByFechaCitaAndHoraCitaSolicitudId(fechaCita, idHoraCita);
        
            if(existsCita){
                throw new UniqueValidationException("Ya existe la cita de solicitud con la fecha y Hora de cita ingresados");
            } else {
                citaSolicitudAdopcionDB.setFechaCita(citaSolicitudAdopcion.getFechaCita());
                citaSolicitudAdopcionDB.setHoraCitaSolicitud(citaSolicitudAdopcion.getHoraCitaSolicitud());
            }
        } else {
            throw new IllegalArgumentException("El argumento fechaCita o hora de cita no puede ser nulo");
        }

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

        citaSolicitudAdopcionRepository.delete(citaSolicitudAdopcion);
        
        return citaSolicitudAdopcion;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean getExistsCitaSolicitudAdopcionByFechaCita(String fecha) {
        if (fecha == null)
            throw new IllegalArgumentException("El argumento fecha no puede ser nulo");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaCita = null;
        try {
            fechaCita = formatoFecha.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (fechaCita == null)
            throw new IllegalArgumentException("El argumento fechaCita no puede ser nulo");

        return citaSolicitudAdopcionRepository.existsCitaSolicitudAdopcionByFechaCita(fechaCita);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean getExistsCitaSolicitudAdopcionByHoraCitaSolicitudId(Long idHoraCitaSolicitud) {
        if (idHoraCitaSolicitud == null)
            throw new IllegalArgumentException("El argumento idHoraCitaSolicitud no puede ser nulo");
        
        return citaSolicitudAdopcionRepository.existsCitaSolicitudAdopcionByHoraCitaSolicitudId(idHoraCitaSolicitud);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean getExistsCitaSolicitudAdopcionByFechaCitaAndHoraCitaSolicitudId(String fecha, Long idHoraCitaSolicitud) {
        if (idHoraCitaSolicitud == null)
            throw new IllegalArgumentException("El argumento idHoraCitaSolicitud no puede ser nulo");
        
        if (fecha == null)
            throw new IllegalArgumentException("El argumento fecha no puede ser nulo");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaCita = null;
        try {
            fechaCita = formatoFecha.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (fechaCita == null)
            throw new IllegalArgumentException("El argumento fechaCita no puede ser nulo");
        
        return citaSolicitudAdopcionRepository.existsCitaSolicitudAdopcionByFechaCitaAndHoraCitaSolicitudId(fechaCita, idHoraCitaSolicitud);
    }

}
