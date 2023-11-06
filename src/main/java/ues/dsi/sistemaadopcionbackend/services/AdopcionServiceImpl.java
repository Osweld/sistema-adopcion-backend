package ues.dsi.sistemaadopcionbackend.services;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.DTO.GenerateAdopcionDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Adopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.CitaSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoCitaSolicitud;
import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoMascota;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.models.repository.AdopcionRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.CitaSolicitudAdopcionRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.MascotaRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.SolicitudAdopcionRepository;

@Service
public class AdopcionServiceImpl implements AdopcionService{

    private final AdopcionRepository adopcionRepository;
    private final MascotaRepository mascotaRepository;
    private final CitaSolicitudAdopcionRepository citaSolicitudAdopcionRepository;
    private final SolicitudAdopcionRepository solicitudAdopcionRepository;

    public AdopcionServiceImpl(AdopcionRepository adopcionRepository, MascotaRepository mascotaRepository, CitaSolicitudAdopcionRepository citaSolicitudAdopcionRepository, SolicitudAdopcionRepository solicitudAdopcionRepository) {
        this.adopcionRepository = adopcionRepository;
        this.mascotaRepository = mascotaRepository;
        this.citaSolicitudAdopcionRepository = citaSolicitudAdopcionRepository;
        this.solicitudAdopcionRepository = solicitudAdopcionRepository;
    }


    @Override
    @Transactional
    public Adopcion createAdopcion(GenerateAdopcionDTO generateAdopcionDTO) {
        Usuario usuario = new Usuario(generateAdopcionDTO.getIdUsuario());
        Mascota mascota = mascotaRepository.findById(generateAdopcionDTO.getIdMascota()).orElseThrow();
        if(mascota.getEstadoMascota().getId() == 3L){
            throw new IllegalArgumentException("No se puede adoptar una mascota que ya este adoptada");
        }
        mascota.setEstadoMascota(new EstadoMascota(3L));
        mascotaRepository.save(mascota);
        Adopcion adopcion = new Adopcion();
        adopcion.setUsuario(usuario);
        adopcion.setMascota(mascota);
        adopcionRepository.save(adopcion);
        
        Long mascotaId = mascota.getId();
        Long usuarioId = usuario.getId();
        Boolean existsSolicitudesMascota = solicitudAdopcionRepository.existsSolicitudAdopcionByMascotaId(mascotaId);
        
        if(existsSolicitudesMascota){
            List<SolicitudAdopcion> solicitudesAdopcionMascota = solicitudAdopcionRepository.findAllByMascotaId(mascotaId);
            if(solicitudesAdopcionMascota == null)
                throw new IllegalArgumentException("La lista de solicitudes por la mascota no puede ser nula");
            
            for (SolicitudAdopcion solicitudAdopcion : solicitudesAdopcionMascota) {
                Long idEstadoSolicitud = solicitudAdopcion.getEstadoSolicitudAdopcion().getId();
                
                if (idEstadoSolicitud != 2L) {
                    
                    if (idEstadoSolicitud == 5L) {
                        Long idSolicitud = solicitudAdopcion.getId();
                        Boolean existsCita = citaSolicitudAdopcionRepository.existsCitaSolicitudAdopcionBySolicitudAdopcionId(idSolicitud);

                        if (existsCita){
                            List<CitaSolicitudAdopcion> citasSolicitud = citaSolicitudAdopcionRepository.findBySolicitudAdopcionId(idSolicitud);
                            
                            for (CitaSolicitudAdopcion citaSolicitud : citasSolicitud) {
                                if (solicitudAdopcion.getUsuario().getId().equals(usuarioId)){
                                    citaSolicitud.setDescripcion("Se completó la cita y fue adoptada la mascota");
                                    citaSolicitud.setEstadoCitaSolicitud(new EstadoCitaSolicitud(6L));
                                } else {
                                    citaSolicitud.setDescripcion("Se cancela la cita debido a que la mascota ya fue adoptada");
                                    citaSolicitud.setEstadoCitaSolicitud(new EstadoCitaSolicitud(5L));
                                }
                                citaSolicitudAdopcionRepository.save(citaSolicitud);
                            }
                        }

                    } else if (idEstadoSolicitud == 1L){
                        solicitudAdopcion.setComentarioGestionSolicitud("Se cancela la aprobación de la solicitud de adopción, esto debido a que la mascota ya fue adoptada. Puede optar por iniciar el proceso por otra mascota si así lo desea.");
                        solicitudAdopcion.setEstadoSolicitudAdopcion(new EstadoSolicitudAdopcion(2L));
                        solicitudAdopcionRepository.save(solicitudAdopcion);
                    } else {
                        solicitudAdopcion.setComentarioGestionSolicitud("Se deniega la solicitud de adopción, debido a que la mascota ya fue adoptada. Puede optar por iniciar el proceso por otra mascota.");
                        solicitudAdopcion.setEstadoSolicitudAdopcion(new EstadoSolicitudAdopcion(2L));
                        solicitudAdopcionRepository.save(solicitudAdopcion);
                    }
                }
            }
        }
        
        return adopcion;
    }

    @Override
    @Transactional
    public Adopcion deleteAdopcion(Long idAdopcion) {
        Adopcion adopcion = adopcionRepository.findById(idAdopcion).orElseThrow();
        Mascota mascota = adopcion.getMascota();
        mascota.setEstadoMascota(new EstadoMascota(1L));
        mascotaRepository.save(mascota);
        adopcionRepository.delete(adopcion);
        
        Long mascotaId = adopcion.getMascota().getId();
        Boolean existsSolicitudesMascota = solicitudAdopcionRepository.existsSolicitudAdopcionByMascotaId(mascotaId);
        
        if(existsSolicitudesMascota){
            List<SolicitudAdopcion> solicitudesAdopcionMascota = solicitudAdopcionRepository.findAllByMascotaId(mascotaId);
            if(solicitudesAdopcionMascota == null)
                throw new IllegalArgumentException("La lista de solicitudes por la mascota no puede ser nula");
            
            for (SolicitudAdopcion solicitudAdopcion : solicitudesAdopcionMascota) {
                Long idEstadoSolicitud = solicitudAdopcion.getEstadoSolicitudAdopcion().getId();
                
                if (idEstadoSolicitud == 5L) {
                    Long idSolicitud = solicitudAdopcion.getId();
                    Boolean existsCita = citaSolicitudAdopcionRepository.existsCitaSolicitudAdopcionBySolicitudAdopcionId(idSolicitud);

                    if (existsCita){
                        List<CitaSolicitudAdopcion> citasSolicitud = citaSolicitudAdopcionRepository.findBySolicitudAdopcionId(idSolicitud);
                        
                        for (CitaSolicitudAdopcion citaSolicitud : citasSolicitud) {
                            if (citaSolicitud.getFechaCita().after(new Date()) && citaSolicitud.getEstadoCitaSolicitud().getId().equals(5L)){
                                citaSolicitud.setDescripcion("Se habilita la cita de nuevo");
                                citaSolicitud.setEstadoCitaSolicitud(new EstadoCitaSolicitud(1L));
                                citaSolicitudAdopcionRepository.save(citaSolicitud);
                            }
                        }
                    }

                } else if (idEstadoSolicitud == 2L){
                    solicitudAdopcion.setComentarioGestionSolicitud("");
                    solicitudAdopcion.setEstadoSolicitudAdopcion(new EstadoSolicitudAdopcion(3L));
                    solicitudAdopcionRepository.save(solicitudAdopcion);
                }
            }
        }

        return adopcion;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Adopcion> getAllAdopciones(Pageable pageable) {
        return adopcionRepository.findAll(pageable);
    }
}
