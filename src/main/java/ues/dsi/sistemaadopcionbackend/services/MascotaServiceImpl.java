package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.models.repository.MascotaRepository;

public class MascotaServiceImpl implements MascotaService{

    private final MascotaRepository mascotaRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }


    @Override
    public Page<Mascota> getAllMascotas(Pageable pageable) {
        return mascotaRepository.findAll(pageable);
    }

    @Override
    public Page<Mascota> getAllMascotasByGenero(Long idGenero, Pageable pageable) {
        if (idGenero == null)
            throw new IllegalArgumentException("El argumento idGenero no puede ser nulo");
        return mascotaRepository.findAllByGeneroId(idGenero, pageable);
    }

    @Override
    public Page<Mascota> getAllMascotasByRaza(Long idRaza, Pageable pageable) {
        if (idRaza == null)
            throw new IllegalArgumentException("El argumento idRaza no puede ser nulo");
        return mascotaRepository.findAllByRazaId(idRaza, pageable);
    }

    @Override
    public Page<Mascota> getAllMascotasByEspecie(Long idEspecie, Pageable pageable) {
        if (idEspecie == null)
            throw new IllegalArgumentException("El argumento idEspecie no puede ser nulo");
        return mascotaRepository.findAllByEspecieId(idEspecie, pageable);
    }

    @Override
    public Mascota getMascotaById(Long idMascota) {
        return mascotaRepository.findById(idMascota).orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con el ID proporcionado: "+idMascota));
    }

    @Override
    public Mascota createMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
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
