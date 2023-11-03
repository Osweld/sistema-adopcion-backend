package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.DTO.GenerateAdopcionDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Adopcion;
import ues.dsi.sistemaadopcionbackend.services.AdopcionService;

@RestController
@RequestMapping("/api/v1/adopcion")
public class AdopcionController {

    private final AdopcionService adopcionService;

    public AdopcionController(AdopcionService adopcionService) {
        this.adopcionService = adopcionService;
    }

    @GetMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<Adopcion>> getAllAdopcionesWithPagination(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(adopcionService.getAllAdopciones( PageRequest.of(page, size)), HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Adopcion> createAdopcion(@Valid @RequestBody GenerateAdopcionDTO generateAdopcionDTO){
        return new ResponseEntity<Adopcion>(adopcionService.createAdopcion(generateAdopcionDTO),HttpStatus.CREATED);
    }



    @DeleteMapping("/{idAdopcion}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Adopcion> deleteMascota(@PathVariable Long idAdopcion){
        return new ResponseEntity<>(adopcionService.deleteAdopcion(idAdopcion),HttpStatus.OK);
    }


}
