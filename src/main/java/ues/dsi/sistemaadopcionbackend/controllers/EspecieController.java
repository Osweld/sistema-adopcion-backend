package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.Especie;
import ues.dsi.sistemaadopcionbackend.services.EspecieService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/especies")
public class EspecieController {


    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }


    @GetMapping("")
    ResponseEntity<List<Especie>> getAllEspecies(){
        return new ResponseEntity<>(especieService.getAllEspecies(), HttpStatus.OK);
    }

    @GetMapping("/{idEspecie}")
    ResponseEntity<Especie> getAllEspecies(@PathVariable Long idEspecie){
        return new ResponseEntity<>(especieService.getEspecieById(idEspecie), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Especie> createEspecie(@Valid @RequestBody Especie especie){
        return new ResponseEntity<>(especieService.createEspecie(especie),HttpStatus.CREATED);
    }

    @PutMapping("/{idEspecie}")
    ResponseEntity<Especie> editEspecie(@PathVariable Long idEspecie, @Valid @RequestBody Especie especie){
        return new ResponseEntity<>(especieService.editEspecie(idEspecie,especie),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEspecie}")
    ResponseEntity<Especie> deleteEspecieById(@PathVariable Long idEspecie){
        return new ResponseEntity<>(especieService.deleteEspecieById(idEspecie),HttpStatus.OK);
    }
}
