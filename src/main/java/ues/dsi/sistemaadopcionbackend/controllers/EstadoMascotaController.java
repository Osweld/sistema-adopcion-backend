package ues.dsi.sistemaadopcionbackend.controllers;


import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoMascota;
import ues.dsi.sistemaadopcionbackend.services.EstadoMascotaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estado-mascota")
public class EstadoMascotaController {

    private final EstadoMascotaService estadoMascotaService;

    public EstadoMascotaController(EstadoMascotaService estadoMascotaService) {
        this.estadoMascotaService = estadoMascotaService;
    }


    @GetMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<List<EstadoMascota>> getAllEstadoMascotas(){
        return new ResponseEntity<>(estadoMascotaService.getAllEstadoMascota(), HttpStatus.OK);
    }

    @GetMapping("/{idEstadoMascota}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoMascota> getAllEstadoMascotas(@PathVariable Long idEstadoMascota){
        return new ResponseEntity<>(estadoMascotaService.getEstadoMascotaById(idEstadoMascota), HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoMascota> createEstadoMascota(@Valid @RequestBody EstadoMascota estadoMascota){
        return new ResponseEntity<>(estadoMascotaService.createEstadoMascota(estadoMascota),HttpStatus.CREATED);
    }

    @PutMapping("/{idEstadoMascota}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoMascota> editEstadoMascota(@PathVariable Long idEstadoMascota, @Valid @RequestBody EstadoMascota estadoMascota){
        return new ResponseEntity<>(estadoMascotaService.editEstadoMascota(idEstadoMascota,estadoMascota),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEstadoMascota}")
     @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoMascota> deleteEstadoMascotaById(@PathVariable Long idEstadoMascota){
        return new ResponseEntity<>(estadoMascotaService.deleteEstadoMascotaById(idEstadoMascota),HttpStatus.OK);
    }
}
