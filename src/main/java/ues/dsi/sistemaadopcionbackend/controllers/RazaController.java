package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.Raza;
import ues.dsi.sistemaadopcionbackend.services.RazaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/razas")
public class RazaController {

    private final RazaService razaService;

    public RazaController(RazaService razaService) {
        this.razaService = razaService;
    }

    @GetMapping()
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<List<Raza>> getAllRazas(){
        return new ResponseEntity<>(razaService.getAllRazas(), HttpStatus.OK);
    }

    @GetMapping("/especie/{idEspecie}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<List<Raza>> getRazaByEspecieId(@PathVariable Long idEspecie){
        return new ResponseEntity<>(razaService.getAllRazasByEspecieId(idEspecie),HttpStatus.OK);
    }

    @GetMapping("/{idRaza}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Raza> getRazaById(@PathVariable Long idRaza){
        return new ResponseEntity<>(razaService.getRazaById(idRaza),HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Raza> createRaza(@Valid @RequestBody Raza raza){
        return new ResponseEntity<>(razaService.createRaza(raza),HttpStatus.CREATED);
    }

    @PutMapping("/{idRaza}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Raza> editRaza(@PathVariable Long idRaza,@Valid @RequestBody Raza raza){
        return new ResponseEntity<>(razaService.editRaza(idRaza,raza),HttpStatus.OK);
    }

    @DeleteMapping("/{idRaza}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Raza> deleteRaza(@PathVariable Long idRaza){
        return new ResponseEntity<>(razaService.deleteRazaById(idRaza),HttpStatus.OK);
    }


}
