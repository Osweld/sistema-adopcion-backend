package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;

import java.sql.Time;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.dsi.sistemaadopcionbackend.models.entity.HoraCitaSolicitud;
import ues.dsi.sistemaadopcionbackend.models.repository.HoraCitaSolicitudRepository;

@Service
public class HoraCitaSolicitudServiceImpl implements HoraCitaSolicitudService{

    private final HoraCitaSolicitudRepository horaCitaSolicitudRepository;
   
    public HoraCitaSolicitudServiceImpl(HoraCitaSolicitudRepository horaCitaSolicitudRepository) {
        this.horaCitaSolicitudRepository = horaCitaSolicitudRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HoraCitaSolicitud> getAllHorasCitaSolicitud() {
        return horaCitaSolicitudRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public HoraCitaSolicitud getHoraCitaSolicitudByHoraCita(Time horaCita) {
        if (horaCita == null) {
            throw new IllegalArgumentException("El argumento horaCita no puede ser nulo");
        }

        HoraCitaSolicitud horaCitaSolicitud= horaCitaSolicitudRepository.findByHoraCita(horaCita);
        if (horaCitaSolicitud == null) {
            throw new EntityNotFoundException("Hora Cita no encontrada con el ID proporcionado: "+horaCita);
        }
        return horaCitaSolicitud;
    }

    @Override
    @Transactional(readOnly = true)
    public HoraCitaSolicitud getHoraCitaSolicitudById(Long idHoraCitaSolicitud) {
        return horaCitaSolicitudRepository.findById(idHoraCitaSolicitud).orElseThrow(() -> new EntityNotFoundException("HoraCita no encontrada con el ID proporcionado: "+idHoraCitaSolicitud));
    }

    @Override
    @Transactional
    public HoraCitaSolicitud createHoraCitaSolicitud(HoraCitaSolicitud horaCitaSolicitud) {
        return horaCitaSolicitudRepository.save(horaCitaSolicitud);
    }

    @Override
    @Transactional
    public HoraCitaSolicitud editHoraCitaSolicitud(Long idHoraCitaSolicitud, HoraCitaSolicitud horaCitaSolicitud) {
        if(idHoraCitaSolicitud == null || horaCitaSolicitud == null){
            throw new IllegalArgumentException("Los argumentos idHoraCitaSolicitud y horaCitaSolicitud no pueden ser nulos");
        }
        HoraCitaSolicitud horaCitaSolicitudDB = horaCitaSolicitudRepository.findById(idHoraCitaSolicitud).orElseThrow(() -> new EntityNotFoundException("HoraCita no encontrada con el ID proporcionado: "+idHoraCitaSolicitud));

        if(horaCitaSolicitud.getHoraCita() != null) horaCitaSolicitudDB.setHoraCita(horaCitaSolicitud.getHoraCita());

        return horaCitaSolicitudRepository.save(horaCitaSolicitudDB);
    }

    @Override
    @Transactional
    public HoraCitaSolicitud deleteHoraCitaSolicitudById(Long idHoraCitaSolicitud) {
        if (idHoraCitaSolicitud == null) {
            throw new IllegalArgumentException("El argumento idHoraCitaSolicitud no puede ser nulo");
        }
        HoraCitaSolicitud horaCitaSolicitud = horaCitaSolicitudRepository.findById(idHoraCitaSolicitud).orElseThrow(() ->
                new EntityNotFoundException("Hora Cita no encontrada con el ID proporcionado: " + idHoraCitaSolicitud));
        
        horaCitaSolicitudRepository.delete(horaCitaSolicitud);

        return horaCitaSolicitud;
    }

}
