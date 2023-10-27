package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ues.dsi.sistemaadopcionbackend.models.DTO.CreateSolicitudAdopcionDTO;
import ues.dsi.sistemaadopcionbackend.models.DTO.VerificarSolicitudDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.models.repository.EstadoSolicitudAdopcionRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.MascotaRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.SolicitudAdopcionRepository;

import java.security.Principal;

@Service
public class SolicitudAdopcionServiceImpl implements SolicitudAdopcionService{

    private final SolicitudAdopcionRepository solicitudAdopcionRepository;
    private final EstadoSolicitudAdopcionRepository estadoSolicitudAdopcionRepository;
    private final MascotaRepository mascotaRepository;

    public SolicitudAdopcionServiceImpl(SolicitudAdopcionRepository solicitudAdopcionRepository, EstadoSolicitudAdopcionRepository estadoSolicitudAdopcionRepository,
                                        MascotaRepository mascotaRepository) {
        this.solicitudAdopcionRepository = solicitudAdopcionRepository;
        this.estadoSolicitudAdopcionRepository = estadoSolicitudAdopcionRepository;
        this.mascotaRepository = mascotaRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<SolicitudAdopcion> getAllSolicitudesAdopcion(Pageable pageable) {

        return solicitudAdopcionRepository.findAllByEstadoSolicitudAdopcionId(3L,pageable);
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
    public SolicitudAdopcion getSolicitudByUsuarioAndEstadoSolicitud(Principal principal) {
        Usuario usuario = new Usuario(Long.parseLong(principal.getName()));
        EstadoSolicitudAdopcion estadoSolicitudAdopcion = new EstadoSolicitudAdopcion(3L);
        return solicitudAdopcionRepository.getSolicitudAdopcionByUsuarioAndEstadoSolicitudAdopcion(usuario,estadoSolicitudAdopcion).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public SolicitudAdopcion getSolicitudAdopcionById(Long idSolicitudAdopcion) {
        return solicitudAdopcionRepository.findById(idSolicitudAdopcion).orElseThrow(() -> new EntityNotFoundException("Solicitud Adopcion no encontrada con el ID proporcionado: "+idSolicitudAdopcion));
    }

    @Override
    @Transactional
    public SolicitudAdopcion createSolicitudAdopcion(CreateSolicitudAdopcionDTO createSolicitudAdopcionDTO, Principal principal) {
        Usuario usuario = new Usuario(Long.parseLong(principal.getName()));
        EstadoSolicitudAdopcion estadoSolicitudAdopcion = new EstadoSolicitudAdopcion(3);
        if(solicitudAdopcionRepository.existsSolicitudAdopcionByUsuarioAndEstadoSolicitudAdopcion(usuario,estadoSolicitudAdopcion)){
            throw new IllegalArgumentException("Solo se puede realizar una solicitud a la vez");
        }
        SolicitudAdopcion solicitudAdopcion = new SolicitudAdopcion();
        solicitudAdopcion.setMotivo(createSolicitudAdopcionDTO.getTitulo());
        solicitudAdopcion.setDescripcion(createSolicitudAdopcionDTO.getDescripcion());
        solicitudAdopcion.setUsuario(usuario);
        solicitudAdopcion.setMascota(new Mascota(createSolicitudAdopcionDTO.getIdMascota()));
        solicitudAdopcion.setEstadoSolicitudAdopcion(estadoSolicitudAdopcion);
        return solicitudAdopcionRepository.save(solicitudAdopcion);
    }

    @Override
    @Transactional
    public SolicitudAdopcion verificarSolicitudAdopcion(VerificarSolicitudDTO verificarSolicitudDTO) {

        SolicitudAdopcion solicitudAdopcionDB = solicitudAdopcionRepository.findById(verificarSolicitudDTO.getIdSolicitud()).orElseThrow(() -> new EntityNotFoundException("Solicitud Adopcion no encontrada con el ID proporcionado: "+verificarSolicitudDTO.getIdSolicitud()));

        solicitudAdopcionDB.setEstadoSolicitudAdopcion(new EstadoSolicitudAdopcion(verificarSolicitudDTO.getIdEstadoSolicitud()));
        solicitudAdopcionDB.setComentarioGestionSolicitud(verificarSolicitudDTO.getComentarios());

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

    @Override
    public Page<SolicitudAdopcion> getSolicitudAdopcionByUsuarioAndEStadoAdopcion(Principal principal,Pageable pageable) {
        return solicitudAdopcionRepository.findAllByUsuarioAndEstadoSolicitudAdopcion(
                new Usuario(Long.parseLong(principal.getName())),new EstadoSolicitudAdopcion(2L),pageable);
    }

}
