package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSalud;
import ues.dsi.sistemaadopcionbackend.models.repository.EstadoSaludRepository;

import java.util.List;

@Service
public class EstadoSaludServiceImpl implements EstadoSaludService {

    private final EstadoSaludRepository estadoSaludRepository;

    public EstadoSaludServiceImpl(EstadoSaludRepository estadoSaludRepository) {
        this.estadoSaludRepository = estadoSaludRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoSalud> getAllEstadoSalud() {
        return estadoSaludRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoSalud getEstadoSaludById(Long idEstadoSalud) {
        return estadoSaludRepository.findById(idEstadoSalud).orElseThrow(() ->
                new EntityNotFoundException("EstadoSalud no encontrada con el ID proporcionado: " + idEstadoSalud));
    }

    @Override
    @Transactional()
    public EstadoSalud createEstadoSalud(EstadoSalud estadoSalud) {
        return estadoSaludRepository.save(estadoSalud);
    }

    @Override
    @Transactional()
    public EstadoSalud editEstadoSalud(Long idEstadoSalud, EstadoSalud estadoSalud) {
        if (idEstadoSalud == null || estadoSalud == null) {
            throw new IllegalArgumentException("Los argumentos idEstadoSalud y EstadoSalud no pueden ser nulos");
        }

        EstadoSalud estadoSaludDB = estadoSaludRepository.findById(idEstadoSalud).orElseThrow(() ->
                new EntityNotFoundException("EstadoSalud no encontrada con el ID proporcionado: " + idEstadoSalud));
        estadoSaludDB.setEstado(estadoSalud.getEstado());
        return estadoSaludRepository.save(estadoSaludDB);
    }

    @Override
    @Transactional()
    public EstadoSalud deleteEstadoSaludById(Long idEstadoSalud) {
        if (idEstadoSalud == null) {
            throw new IllegalArgumentException("El argumento idEstadoSalud no puede ser nulo");
        }

        EstadoSalud estadoSalud = estadoSaludRepository.findById(idEstadoSalud).orElseThrow(() ->
                new EntityNotFoundException("EstadoSalud no encontrada con el ID proporcionado: " + idEstadoSalud));

        estadoSaludRepository.delete(estadoSalud);
        return estadoSalud;
    }
}
