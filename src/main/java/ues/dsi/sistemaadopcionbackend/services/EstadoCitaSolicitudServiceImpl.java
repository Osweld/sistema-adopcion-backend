package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoCitaSolicitud;
import ues.dsi.sistemaadopcionbackend.models.repository.EstadoCitaSolicitudRepository;

import java.util.List;

@Service
public class EstadoCitaSolicitudServiceImpl implements EstadoCitaSolicitudService {

    private final EstadoCitaSolicitudRepository estadoCitaSolicitudRepository;

    public EstadoCitaSolicitudServiceImpl(EstadoCitaSolicitudRepository estadoCitaSolicitudRepository) {
        this.estadoCitaSolicitudRepository = estadoCitaSolicitudRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoCitaSolicitud> getAllEstadoCitaSolicitud() {
        return estadoCitaSolicitudRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoCitaSolicitud getEstadoCitaSolicitudById(Long idEstadoCitaSolicitud) {
        return estadoCitaSolicitudRepository.findById(idEstadoCitaSolicitud).orElseThrow(() ->
                new EntityNotFoundException("Estado Solicitud Adopcion no encontrado con el ID proporcionado: " + idEstadoCitaSolicitud));
    }

    @Override
    @Transactional()
    public EstadoCitaSolicitud createEstadoCitaSolicitud(EstadoCitaSolicitud estadoCitaSolicitud) {
        return estadoCitaSolicitudRepository.save(estadoCitaSolicitud);
    }

    @Override
    @Transactional()
    public EstadoCitaSolicitud editEstadoCitaSolicitud(Long idEstadoCitaSolicitud, EstadoCitaSolicitud estadoCitaSolicitud) {
        if (idEstadoCitaSolicitud == null || estadoCitaSolicitud == null) {
            throw new IllegalArgumentException("Los argumentos idEstadoCitaSolicitud y EstadoCitaSolicitud no pueden ser nulos");
        }

        EstadoCitaSolicitud estadoCitaSolicitudDB = estadoCitaSolicitudRepository.findById(idEstadoCitaSolicitud).orElseThrow(() ->
                new EntityNotFoundException("Estado Solicitud Adopcion no encontrado con el ID proporcionado: " + idEstadoCitaSolicitud));
        estadoCitaSolicitudDB.setEstado(estadoCitaSolicitud.getEstado());
        return estadoCitaSolicitudRepository.save(estadoCitaSolicitudDB);
    }

    @Override
    @Transactional()
    public EstadoCitaSolicitud deleteEstadoCitaSolicitudById(Long idEstadoCitaSolicitud) {
        if (idEstadoCitaSolicitud == null) {
            throw new IllegalArgumentException("El argumento idEstadoCitaSolicitud no puede ser nulo");
        }

        EstadoCitaSolicitud estadoCitaSolicitud = estadoCitaSolicitudRepository.findById(idEstadoCitaSolicitud).orElseThrow(() ->
                new EntityNotFoundException("Estado Solicitud Adopcion no encontrado con el ID proporcionado: " + idEstadoCitaSolicitud));

        estadoCitaSolicitudRepository.delete(estadoCitaSolicitud);
        return estadoCitaSolicitud;
    }
}
