package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.models.repository.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService{

    private final MascotaRepository mascotaRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Mascota> getAllMascotas(Pageable pageable) {
        return mascotaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Mascota> getAllMascotasByGenero(Long idGenero, Pageable pageable) {
        if (idGenero == null)
            throw new IllegalArgumentException("El argumento idGenero no puede ser nulo");
        return mascotaRepository.findAllByGeneroId(idGenero, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Mascota> getAllMascotasByRaza(Long idRaza, Pageable pageable) {
        if (idRaza == null)
            throw new IllegalArgumentException("El argumento idRaza no puede ser nulo");
        return mascotaRepository.findAllByRazaId(idRaza, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Mascota> getAllMascotasByEspecie(Long idEspecie, Pageable pageable) {
        if (idEspecie == null)
            throw new IllegalArgumentException("El argumento idEspecie no puede ser nulo");
        return mascotaRepository.findAllByEspecieId(idEspecie, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Mascota getMascotaById(Long idMascota) {
        return mascotaRepository.findById(idMascota).orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con el ID proporcionado: "+idMascota));
    }

    @Override
    @Transactional
    public Mascota createMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    @Transactional
    public Mascota editMascota(Long idMascota, Mascota mascota) {
        if(idMascota == null || mascota == null){
            throw new IllegalArgumentException("Los argumentos idMascota y mascota no pueden ser nulos");
        }
        Mascota mascotaDB = mascotaRepository.findById(idMascota).orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con el ID proporcionado: "+idMascota));

        mascotaDB.setNombre(mascota.getNombre());
        if(mascota.getColor() != null) mascotaDB.setColor(mascota.getColor());
        if(mascota.getEstadoSalud() != null) mascotaDB.setEstadoSalud(mascota.getEstadoSalud());
        if(mascota.getDescripcion() != null) mascotaDB.setDescripcion(mascota.getDescripcion());
        if(mascota.getFechaNacimiento() != null) mascotaDB.setFechaNacimiento(mascota.getFechaNacimiento());
        if(mascota.getEspecie() != null) mascotaDB.setEspecie(mascota.getEspecie());
        if(mascota.getRaza() != null) mascotaDB.setRaza(mascota.getRaza());
        if(mascota.getGenero() != null) mascotaDB.setGenero(mascota.getGenero());


        return mascotaRepository.save(mascotaDB);
    }

    @Override
    @Transactional
    public Mascota deleteMascotaById(Long idMascota) {
        if (idMascota == null) {
            throw new IllegalArgumentException("El argumento idMascota no puede ser nulo");
        }
        Mascota mascota = mascotaRepository.findById(idMascota).orElseThrow(() ->
                new EntityNotFoundException("Mascota no encontrada con el ID proporcionado: " + idMascota));
        mascotaRepository.delete(mascota);
        return mascota;
    }
}
