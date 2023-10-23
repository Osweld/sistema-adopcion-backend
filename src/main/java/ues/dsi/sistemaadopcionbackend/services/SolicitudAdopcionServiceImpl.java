package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.repository.EstadoSolicitudAdopcionRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.SolicitudAdopcionRepository;

@Service
public class SolicitudAdopcionServiceImpl implements SolicitudAdopcionService{

    private final SolicitudAdopcionRepository solicitudAdopcionRepository;
    private final EstadoSolicitudAdopcionRepository estadoSolicitudAdopcionRepository;
   
    public SolicitudAdopcionServiceImpl(SolicitudAdopcionRepository solicitudAdopcionRepository, EstadoSolicitudAdopcionRepository estadoSolicitudAdopcionRepository) {
        this.solicitudAdopcionRepository = solicitudAdopcionRepository;
        this.estadoSolicitudAdopcionRepository = estadoSolicitudAdopcionRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<SolicitudAdopcion> getAllSolicitudesAdopcion(Pageable pageable) {
        return solicitudAdopcionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SolicitudAdopcion> getAllSolicitudesAdopcionByMascotaId(Long idMascota, Pageable pageable) {
        if (idMascota == null)
            throw new IllegalArgumentException("El argumento idMascota no puede ser nulo");
        return solicitudAdopcionRepository.findAllByMascotaId(idMascota, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SolicitudAdopcion> getAllSolicitudesAdopcionByUsuarioId(Long idUsuario, Pageable pageable) {
        if (idUsuario == null)
            throw new IllegalArgumentException("El argumento idUsuario no puede ser nulo");
        return solicitudAdopcionRepository.findAllByUsuarioId(idUsuario, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SolicitudAdopcion> getAllSolicitudesAdopcionByEstadoSolicitudAdopcion(Long idEstadoSolicitudAdopcion, Pageable pageable) {
        if (idEstadoSolicitudAdopcion == null)
            throw new IllegalArgumentException("El argumento idEstadoSolicitudAdopcion no puede ser nulo");
        return solicitudAdopcionRepository.findAllByEstadoSolicitudAdopcionId(idEstadoSolicitudAdopcion, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public SolicitudAdopcion getSolicitudAdopcionById(Long idSolicitudAdopcion) {
        return solicitudAdopcionRepository.findById(idSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("Solicitud Adopcion no encontrada con el ID proporcionado: "+idSolicitudAdopcion));
    }

    @Override
    @Transactional
    public SolicitudAdopcion createSolicitudAdopcion(SolicitudAdopcion solicitudAdopcion) {
        return solicitudAdopcionRepository.save(solicitudAdopcion);
    }

    @Override
    @Transactional
    public SolicitudAdopcion editSolicitudAdopcion(Long idSolicitudAdopcion, SolicitudAdopcion solicitudAdopcion) {
        if(idSolicitudAdopcion == null || solicitudAdopcion == null){
            throw new IllegalArgumentException("Los argumentos idSolicitudAdopcion y solicitudAdopcion no pueden ser nulos");
        }
        SolicitudAdopcion solicitudAdopcionDB = solicitudAdopcionRepository.findById(idSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("Solicitud Adopcion no encontrada con el ID proporcionado: "+idSolicitudAdopcion));

        if(solicitudAdopcion.getMotivo() != null) solicitudAdopcionDB.setMotivo(solicitudAdopcion.getMotivo());
        if(solicitudAdopcion.getDescripcion() != null) solicitudAdopcionDB.setDescripcion(solicitudAdopcion.getDescripcion());
        if(solicitudAdopcion.getMascota() != null) solicitudAdopcionDB.setMascota(solicitudAdopcion.getMascota());
        if(solicitudAdopcion.getUsuario() != null) solicitudAdopcionDB.setUsuario(solicitudAdopcion.getUsuario());
        if(solicitudAdopcion.getEstadoSolicitudAdopcion() != null) solicitudAdopcionDB.setEstadoSolicitudAdopcion(solicitudAdopcion.getEstadoSolicitudAdopcion());
        if(solicitudAdopcion.getComentarioGestionSolicitud() != null) solicitudAdopcionDB.setComentarioGestionSolicitud(solicitudAdopcion.getComentarioGestionSolicitud());

        return solicitudAdopcionRepository.save(solicitudAdopcionDB);
    }

    @Override
    @Transactional
    public SolicitudAdopcion deleteSolicitudAdopcionById(Long idSolicitudAdopcion) {
        if (idSolicitudAdopcion == null) {
            throw new IllegalArgumentException("El argumento idSolicitudAdopcion no puede ser nulo");
        }
        SolicitudAdopcion solicitudAdopcion = solicitudAdopcionRepository.findById(idSolicitudAdopcion).orElseThrow(() ->
                new EntityNotFoundException("Solicitud Adopción no encontrada con el ID proporcionado: " + idSolicitudAdopcion));

        EstadoSolicitudAdopcion estadoSolicitudAdopcion = estadoSolicitudAdopcionRepository.findByEstado("BORRADA");
        if(estadoSolicitudAdopcion == null)
            throw new IllegalArgumentException("Estado Solicitud Adopción no encontrado con el Estado proporcionado: BORRADA");
        
        solicitudAdopcion.setEstadoSolicitudAdopcion(estadoSolicitudAdopcion);
        
        return solicitudAdopcionRepository.save(solicitudAdopcion);
    }

}
