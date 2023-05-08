package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.Genero;
import ues.dsi.sistemaadopcionbackend.services.GeneroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }


    @GetMapping("")
    ResponseEntity<List<Genero>> getAllGeneros(){
        return new ResponseEntity<>(generoService.getAllGeneros(), HttpStatus.OK);
    }

    @GetMapping("/{idGenero}")
    ResponseEntity<Genero> getAllGeneros(@PathVariable Long idGenero){
        return new ResponseEntity<>(generoService.getGeneroById(idGenero), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Genero> createGenero(@Valid @RequestBody Genero genero){
        return new ResponseEntity<>(generoService.createGenero(genero),HttpStatus.CREATED);
    }

    @PutMapping("/{idGenero}")
    ResponseEntity<Genero> editGenero(@PathVariable Long idGenero, @Valid @RequestBody Genero genero){
        return new ResponseEntity<>(generoService.editGenero(idGenero,genero),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idGenero}")
    ResponseEntity<Genero> deleteGeneroById(@PathVariable Long idGenero){
        return new ResponseEntity<>(generoService.deleteGeneroById(idGenero),HttpStatus.OK);
    }

}
