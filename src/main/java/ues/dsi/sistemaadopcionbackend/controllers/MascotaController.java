package ues.dsi.sistemaadopcionbackend.controllers;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ues.dsi.sistemaadopcionbackend.models.entity.Foto;
import ues.dsi.sistemaadopcionbackend.models.entity.Mascota;
import ues.dsi.sistemaadopcionbackend.services.MascotaService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;


    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping("")
    ResponseEntity<Page<Mascota>> getAllMascotasWithPagination(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                               @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(mascotaService.getAllMascotas(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/raza/{idRaza}")
    ResponseEntity<Page<Mascota>> getMascotasByRazaWithPagination(@PathVariable Long idRaza,
                                                                  @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                  @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(mascotaService.getAllMascotasByRaza(idRaza,PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/especie/{idEspecie}")
    ResponseEntity<Page<Mascota>> getMascotasByEspecieWithPagination(@PathVariable Long idEspecie,
                                                                  @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                  @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(mascotaService.getAllMascotasByEspecie(idEspecie,PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/genero/{idGenero}")
    ResponseEntity<Page<Mascota>> getMascotasByGeneroWithPagination(@PathVariable Long idGenero,
                                                                     @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                     @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(mascotaService.getAllMascotasByGenero(idGenero,PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{idMascota}")
    ResponseEntity<Mascota> getMascotaById(@PathVariable Long idMascota){
        return new ResponseEntity<>(mascotaService.getMascotaById(idMascota),HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Mascota> createMascota(@Valid @RequestBody Mascota mascota){
        return new ResponseEntity<>(mascotaService.createMascota(mascota),HttpStatus.CREATED);
    }

    @PostMapping("fotos/{idMascota}")
    ResponseEntity<List<Foto>> uploadPhotos(@PathVariable Long idMascota,@RequestPart(name = "imagenes") MultipartFile[] multipartFiles) throws IOException {
        return new ResponseEntity<>(mascotaService.saveMascotaPhotos(idMascota,multipartFiles),HttpStatus.CREATED);
    }

    @PostMapping("foto-perfil/{idMascota}")
    ResponseEntity<Mascota> Perfil(@PathVariable Long idMascota,@RequestPart(name = "imagen") MultipartFile multipartFiles) throws IOException {
        return new ResponseEntity<>(mascotaService.saveMascotaPhotoPerfil(idMascota,multipartFiles),HttpStatus.CREATED);
    }

    @PutMapping("/{idMascota}")
    ResponseEntity<Mascota> editMascota(@PathVariable Long idMascota,
                                        @Valid @RequestBody Mascota mascota){
        return new ResponseEntity<>(mascotaService.editMascota(idMascota,mascota),HttpStatus.OK);
    }

    @DeleteMapping("/{idMascota}")
    ResponseEntity<Mascota> deleteMascota(@PathVariable Long idMascota){
        return new ResponseEntity<>(mascotaService.deleteMascotaById(idMascota),HttpStatus.OK);
    }


}
