package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ues.dsi.sistemaadopcionbackend.models.entity.Genero;
import ues.dsi.sistemaadopcionbackend.models.repository.GeneroRepository;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService{

    private final GeneroRepository generoRepository;

    public GeneroServiceImpl(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genero> getAllGeneros() {
        return generoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genero getGeneroById(Long idGenero) {
        return generoRepository.findById(idGenero).orElseThrow(() ->
                new EntityNotFoundException("Genero no encontrado con el ID proporcionado: " + idGenero));
    }

    @Override
    @Transactional()
    public Genero createGenero(Genero genero) {
        return generoRepository.save(genero);
    }

    @Override
    @Transactional()
    public Genero editGenero(Long idGenero, Genero genero) {
        if (idGenero == null || genero == null) {
            throw new IllegalArgumentException("Los argumentos idGenero y genero no pueden ser nulos");
        }

        Genero generoDB = generoRepository.findById(idGenero).orElseThrow(() ->
                new EntityNotFoundException("Genero no encontrado con el ID proporcionado: " + idGenero));
        generoDB.setNombre(genero.getNombre());
        return generoRepository.save(generoDB);
    }

    @Override
    @Transactional()
    public Genero deleteGeneroById(Long idGenero) {
        if (idGenero == null) {
            throw new IllegalArgumentException("El argumento idGenero no puede ser nulo");
        }

        Genero genero = generoRepository.findById(idGenero).orElseThrow(() ->
                new EntityNotFoundException("Genero no encontrado con el ID proporcionado: " + idGenero));

        generoRepository.delete(genero);
        return genero;
    }
}
