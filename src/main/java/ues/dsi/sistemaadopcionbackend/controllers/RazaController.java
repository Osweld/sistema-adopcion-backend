package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<Raza>> getAllRazas(){
        return new ResponseEntity<>(razaService.getAllRazas(), HttpStatus.OK);
    }

    @GetMapping("/especie/{idEspecie}")
    ResponseEntity<List<Raza>> getRazaByEspecieId(@PathVariable Long idEspecie){
        return new ResponseEntity<>(razaService.getAllRazasByEspecieId(idEspecie),HttpStatus.OK);
    }

    @GetMapping("/{idRaza}")
    ResponseEntity<Raza> getRazaById(@PathVariable Long idRaza){
        return new ResponseEntity<>(razaService.getRazaById(idRaza),HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Raza> createRaza(@Valid @RequestBody Raza raza){
        return new ResponseEntity<>(razaService.createRaza(raza),HttpStatus.CREATED);
    }

    @PutMapping("/{idRaza}")
    ResponseEntity<Raza> editRaza(@PathVariable Long idRaza,@Valid @RequestBody Raza raza){
        return new ResponseEntity<>(razaService.editRaza(idRaza,raza),HttpStatus.OK);
    }

    @DeleteMapping("/{idRaza}")
    ResponseEntity<Raza> deleteRaza(@PathVariable Long idRaza){
        return new ResponseEntity<>(razaService.deleteRazaById(idRaza),HttpStatus.OK);
    }


}
