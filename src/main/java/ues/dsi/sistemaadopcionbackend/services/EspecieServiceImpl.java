package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.exceptions.DuplicateEntityException;
import ues.dsi.sistemaadopcionbackend.models.entity.Especie;
import ues.dsi.sistemaadopcionbackend.models.repository.EspecieRepository;

import java.util.List;

@Service
public class EspecieServiceImpl implements EspecieService{

    private final EspecieRepository especieRepository;

    public EspecieServiceImpl(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Especie> getAllEspecies() {
        return especieRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Especie getEspecieById(Long idEspecie) {
        return especieRepository.findById(idEspecie).orElseThrow(() ->
                new EntityNotFoundException("Especie no encontrada con el ID proporcionado: " + idEspecie));
    }

    @Override
    @Transactional()
    public Especie createEspecie(Especie especie) {
        if(especieRepository.existsByNombreIgnoreCase(especie.getNombre()))
            throw new DuplicateEntityException("Ya existe una especie con ese nombre: "+especie.getNombre());
        return especieRepository.save(especie);

    }

    @Override
    @Transactional()
    public Especie editEspecie(Long idEspecie, Especie especie) {
        if (idEspecie == null || especie == null) {
            throw new IllegalArgumentException("Los argumentos idEspecie y Especie no pueden ser nulos");
        }

        Especie especieDB = especieRepository.findById(idEspecie).orElseThrow(() ->
                new EntityNotFoundException("Especie no encontrada con el ID proporcionado: " + idEspecie));
        especieDB.setNombre(especie.getNombre());
        return especieRepository.save(especieDB);
    }

    @Override
    @Transactional()
    public Especie deleteEspecieById(Long idEspecie) {
        if (idEspecie == null) {
            throw new IllegalArgumentException("El argumento idEspecie no puede ser nulo");
        }

        Especie especie = especieRepository.findById(idEspecie).orElseThrow(() ->
                new EntityNotFoundException("Especie no encontrada con el ID proporcionado: " + idEspecie));

        especieRepository.delete(especie);
        return especie;
    }
}
