package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoMascota;
import ues.dsi.sistemaadopcionbackend.models.repository.EstadoMascotaRepository;

import java.util.List;

@Service
public class EstadoMascotaServiceImpl implements EstadoMascotaService {

    private final EstadoMascotaRepository estadoMascotaRepository;

    public EstadoMascotaServiceImpl(EstadoMascotaRepository estadoMascotaRepository) {
        this.estadoMascotaRepository = estadoMascotaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoMascota> getAllEstadoMascota() {
        return estadoMascotaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoMascota getEstadoMascotaById(Long idEstadoMascota) {
        return estadoMascotaRepository.findById(idEstadoMascota).orElseThrow(() ->
                new EntityNotFoundException("EstadoMascota no encontrada con el ID proporcionado: " + idEstadoMascota));
    }

    @Override
    @Transactional()
    public EstadoMascota createEstadoMascota(EstadoMascota estadoMascota) {
        return estadoMascotaRepository.save(estadoMascota);
    }

    @Override
    @Transactional()
    public EstadoMascota editEstadoMascota(Long idEstadoMascota, EstadoMascota estadoMascota) {
        if (idEstadoMascota == null || estadoMascota == null) {
            throw new IllegalArgumentException("Los argumentos idEstadoMascota y EstadoMascota no pueden ser nulos");
        }

        EstadoMascota estadoMascotaDB = estadoMascotaRepository.findById(idEstadoMascota).orElseThrow(() ->
                new EntityNotFoundException("EstadoMascota no encontrada con el ID proporcionado: " + idEstadoMascota));
        estadoMascotaDB.setEstado(estadoMascota.getEstado());
        return estadoMascotaRepository.save(estadoMascotaDB);
    }

    @Override
    @Transactional()
    public EstadoMascota deleteEstadoMascotaById(Long idEstadoMascota) {
        if (idEstadoMascota == null) {
            throw new IllegalArgumentException("El argumento idEstadoMascota no puede ser nulo");
        }

        EstadoMascota estadoMascota = estadoMascotaRepository.findById(idEstadoMascota).orElseThrow(() ->
                new EntityNotFoundException("EstadoMascota no encontrada con el ID proporcionado: " + idEstadoMascota));

        estadoMascotaRepository.delete(estadoMascota);
        return estadoMascota;
    }
}
