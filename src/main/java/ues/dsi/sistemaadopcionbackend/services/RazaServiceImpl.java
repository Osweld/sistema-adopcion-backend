package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.entity.Raza;
import ues.dsi.sistemaadopcionbackend.models.repository.RazaRepository;

import java.util.List;
@Service
public class RazaServiceImpl implements RazaService{

    private final RazaRepository razaRepository;

    public RazaServiceImpl(RazaRepository razaRepository) {
        this.razaRepository = razaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Raza> getAllRazas() {
        return razaRepository.findAll();
    }

    @Override
    public List<Raza> getAllRazasByEspecieId(Long IdEspecie) {
        return razaRepository.findAllByEspecieId(IdEspecie);
    }

    @Override
    @Transactional(readOnly = true)
    public Raza getRazaById(Long idRaza) {
        return razaRepository.findById(idRaza).orElseThrow(() -> new EntityNotFoundException("Raza no encontrada con el ID proporcionado: "+idRaza));
    }

    @Override
    @Transactional
    public Raza createRaza(Raza raza) {
        return razaRepository.save(raza);
    }

    @Override
    @Transactional
    public Raza editRaza(Long idRaza, Raza raza) {
        if(idRaza == null && raza == null){
            throw new IllegalArgumentException("Los argumentos idRaza y raza no pueden ser nulos");
        }

        Raza razaBD = razaRepository.findById(idRaza).
                orElseThrow(() -> new EntityNotFoundException("Raza no encontrada con el ID proporcionado: "+idRaza));

        razaBD.setNombre(raza.getNombre());
        if(raza.getEspecie() != null) razaBD.setEspecie(raza.getEspecie());
        return razaRepository.save(razaBD);
    }

    @Override
    @Transactional
    public Raza deleteRazaById(Long idRaza) {
        if(idRaza == null){
            throw new IllegalArgumentException("El argumento idGenero no puede ser nulo");
        }

        Raza razaBD = razaRepository.findById(idRaza).
                orElseThrow(() -> new EntityNotFoundException("Raza no encontrada con el ID proporcionado: "+idRaza));
        razaRepository.delete(razaBD);
        return razaBD;
    }
}
