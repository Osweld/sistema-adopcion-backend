package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.repository.EstadoSolicitudAdopcionRepository;

import java.util.List;

@Service
public class EstadoSolicitudAdopcionServiceImpl implements EstadoSolicitudAdopcionService {

    private final EstadoSolicitudAdopcionRepository estadoSolicitudAdopcionRepository;

    public EstadoSolicitudAdopcionServiceImpl(EstadoSolicitudAdopcionRepository estadoSolicitudAdopcionRepository) {
        this.estadoSolicitudAdopcionRepository = estadoSolicitudAdopcionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoSolicitudAdopcion> getAllEstadoSolicitudAdopcion() {
        return estadoSolicitudAdopcionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoSolicitudAdopcion getEstadoSolicitudAdopcionById(Long idEstadoSolicitudAdopcion) {
        return estadoSolicitudAdopcionRepository.findById(idEstadoSolicitudAdopcion).orElseThrow(() ->
                new EntityNotFoundException("Estado Solicitud Adopcion no encontrado con el ID proporcionado: " + idEstadoSolicitudAdopcion));
    }

    @Override
    @Transactional()
    public EstadoSolicitudAdopcion createEstadoSolicitudAdopcion(EstadoSolicitudAdopcion estadoSolicitudAdopcion) {
        return estadoSolicitudAdopcionRepository.save(estadoSolicitudAdopcion);
    }

    @Override
    @Transactional()
    public EstadoSolicitudAdopcion editEstadoSolicitudAdopcion(Long idEstadoSolicitudAdopcion, EstadoSolicitudAdopcion estadoSolicitudAdopcion) {
        if (idEstadoSolicitudAdopcion == null || estadoSolicitudAdopcion == null) {
            throw new IllegalArgumentException("Los argumentos idEstadoSolicitudAdopcion y EstadoSolicitudAdopcion no pueden ser nulos");
        }

        EstadoSolicitudAdopcion estadoSolicitudAdopcionDB = estadoSolicitudAdopcionRepository.findById(idEstadoSolicitudAdopcion).orElseThrow(() ->
                new EntityNotFoundException("Estado Solicitud Adopcion no encontrado con el ID proporcionado: " + idEstadoSolicitudAdopcion));
        estadoSolicitudAdopcionDB.setEstado(estadoSolicitudAdopcion.getEstado());
        return estadoSolicitudAdopcionRepository.save(estadoSolicitudAdopcionDB);
    }

    @Override
    @Transactional()
    public EstadoSolicitudAdopcion deleteEstadoSolicitudAdopcionById(Long idEstadoSolicitudAdopcion) {
        if (idEstadoSolicitudAdopcion == null) {
            throw new IllegalArgumentException("El argumento idEstadoSolicitudAdopcion no puede ser nulo");
        }

        EstadoSolicitudAdopcion estadoSolicitudAdopcion = estadoSolicitudAdopcionRepository.findById(idEstadoSolicitudAdopcion).orElseThrow(() ->
                new EntityNotFoundException("Estado Solicitud Adopcion no encontrado con el ID proporcionado: " + idEstadoSolicitudAdopcion));

        estadoSolicitudAdopcionRepository.delete(estadoSolicitudAdopcion);
        return estadoSolicitudAdopcion;
    }
}
