package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.MeGusta;
import ues.dsi.sistemaadopcionbackend.services.MeGustaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/me-gusta")
public class MeGustaController {

    private final MeGustaService meGustaService;

    public MeGustaController(MeGustaService meGustaService) {
        this.meGustaService = meGustaService;
    }

    @GetMapping()
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<List<MeGusta>> getAllMeGusta(){
        return new ResponseEntity<>(meGustaService.getAllMeGusta(), HttpStatus.OK);
    }

    @GetMapping("/mascota/{idMascota}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<List<MeGusta>> getMeGustaByMascotaId(@PathVariable Long idMascota){
        return new ResponseEntity<>(meGustaService.getAllMeGustaByMascotaId(idMascota),HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    @PermitAll()
    ResponseEntity<List<MeGusta>> getMeGustaByUsuarioId(@PathVariable Long idUsuario){
        return new ResponseEntity<>(meGustaService.getAllMeGustaByUsuarioId(idUsuario),HttpStatus.OK);
    }

    @GetMapping("/{idMeGusta}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<MeGusta> getMeGustaById(@PathVariable Long idMeGusta){
        return new ResponseEntity<>(meGustaService.getMeGustaById(idMeGusta),HttpStatus.OK);
    }

    @PostMapping("")
    @PermitAll()
    ResponseEntity<MeGusta> createMeGusta(@Valid @RequestBody MeGusta meGusta){
        return new ResponseEntity<>(meGustaService.createMeGusta(meGusta),HttpStatus.CREATED);
    }

    @PutMapping("/{idMeGusta}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<MeGusta> editMeGusta(@PathVariable Long idMeGusta,@Valid @RequestBody MeGusta meGusta){
        return new ResponseEntity<>(meGustaService.editMeGusta(idMeGusta,meGusta),HttpStatus.OK);
    }

    @DeleteMapping("/{idMeGusta}")
    @PermitAll()
    ResponseEntity<MeGusta> deleteMeGusta(@PathVariable Long idMeGusta){
        return new ResponseEntity<>(meGustaService.deleteMeGustaById(idMeGusta),HttpStatus.OK);
    }


}
