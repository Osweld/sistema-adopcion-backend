package ues.dsi.sistemaadopcionbackend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ues.dsi.sistemaadopcionbackend.cloudinary.FileUpload;
import ues.dsi.sistemaadopcionbackend.models.entity.Foto;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoMascota;
import ues.dsi.sistemaadopcionbackend.models.repository.EstadoMascotaRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.FotoRepository;
import ues.dsi.sistemaadopcionbackend.models.repository.MascotaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements MascotaService{

    private final MascotaRepository mascotaRepository;
    private final FotoRepository fotoRepository;
    private final EstadoMascotaRepository estadoMascotaRepository;
    private final FileUpload fileUpload;

    public MascotaServiceImpl(MascotaRepository mascotaRepository, FotoRepository fotoRepository, EstadoMascotaRepository estadoMascotaRepository, FileUpload fileUpload) {
        this.mascotaRepository = mascotaRepository;
        this.fotoRepository = fotoRepository;
        this.estadoMascotaRepository = estadoMascotaRepository;
        this.fileUpload = fileUpload;
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
    public Page<Mascota> getAllMascotasByEstadoMascota(Long idEstadoMascota, Pageable pageable) {
        if (idEstadoMascota == null)
            throw new IllegalArgumentException("El argumento idEstadoMascota no puede ser nulo");
        return mascotaRepository.findAllByEstadoMascotaId(idEstadoMascota, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Mascota getMascotaById(Long idMascota) {
        return mascotaRepository.findById(idMascota).orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada con el ID proporcionado: "+idMascota));
    }

    @Override
    @Transactional
    public Mascota createMascota(Mascota mascota) {
        EstadoMascota estadoMascota = new EstadoMascota(1);
        mascota.setEstadoMascota(estadoMascota);
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
        if(mascota.getEstadoMascota() != null) mascotaDB.setEstadoMascota(mascota.getEstadoMascota());
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
        
        EstadoMascota estadoMascota = estadoMascotaRepository.findByEstado("NO DISPONIBLE");
        if(estadoMascota == null)
            throw new IllegalArgumentException("Estado Mascota no encontrado con el Estado proporcionado: NO DISPONIBLE");
        
        mascota.setEstadoMascota(estadoMascota);
        
        return mascotaRepository.save(mascota);
    }

    @Override
    @Transactional
    public List<Foto> saveMascotaPhotos(Long idMascota,MultipartFile[] multipartFiles) throws IOException {
        // Validar si el array multipartFiles no está vacío
        if (multipartFiles == null || multipartFiles.length == 0) {
            throw new IllegalArgumentException("No se encontraron fotos");
        }

        // Validar si la mascota existe en la base de datos
        Optional<Mascota> optionalMascota = mascotaRepository.findById(idMascota);
        if (!optionalMascota.isPresent()) {
            throw new IllegalArgumentException("Mascota no encontrada con el ID proporcionado: " + idMascota);
        }
        Mascota mascota = optionalMascota.get();

        List<Foto> fotosDB = getMascotaPhotos(idMascota);
        fotoRepository.deleteAll(fotosDB);

        List<Foto> fotos = new ArrayList<>();
        for(MultipartFile uploadFoto: multipartFiles){
            // Verificar si el archivo no está vacío
            if (!uploadFoto.isEmpty()) {
                Foto foto = new Foto();
                foto.setLink(fileUpload.uploadFile(uploadFoto));
                foto.setMascota(mascota);
                fotos.add(foto);
            }
        }
        return fotoRepository.saveAll(fotos);
    }

    @Override
    @Transactional
    public Mascota saveMascotaPhotoPerfil(Long idMascota, MultipartFile multipartFile) throws IOException {

        if(multipartFile == null) throw new IllegalArgumentException("No se encontraro la foto");

        Optional<Mascota> optionalMascota = mascotaRepository.findById(idMascota);
        if (!optionalMascota.isPresent()) {
            throw new IllegalArgumentException("Mascota no encontrada con el ID proporcionado: " + idMascota);
        }
        Mascota mascota = optionalMascota.get();


        mascota.setFotoPrincipal(fileUpload.uploadFile(multipartFile));

        return mascotaRepository.save(mascota);
    }

    @Override
    public List<Foto> getMascotaPhotos(Long idMascota) {
        if (idMascota == null)
            throw new IllegalArgumentException("El argumento idMascota no puede ser nulo");
        return fotoRepository.findAllByMascotaId(idMascota);
    }
}
