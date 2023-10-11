package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @PermitAll()
    ResponseEntity<List<Genero>> getAllGeneros(){
        return new ResponseEntity<>(generoService.getAllGeneros(), HttpStatus.OK);
    }

    @GetMapping("/{idGenero}")
    @PermitAll()
    ResponseEntity<Genero> getGeneroById(@PathVariable Long idGenero){
        return new ResponseEntity<>(generoService.getGeneroById(idGenero), HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Genero> createGenero(@Valid @RequestBody Genero genero){
        return new ResponseEntity<>(generoService.createGenero(genero),HttpStatus.CREATED);
    }

    @PutMapping("/{idGenero}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Genero> editGenero(@PathVariable Long idGenero, @Valid @RequestBody Genero genero){
        return new ResponseEntity<>(generoService.editGenero(idGenero,genero),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idGenero}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Genero> deleteGeneroById(@PathVariable Long idGenero){
        return new ResponseEntity<>(generoService.deleteGeneroById(idGenero),HttpStatus.OK);
    }

}
