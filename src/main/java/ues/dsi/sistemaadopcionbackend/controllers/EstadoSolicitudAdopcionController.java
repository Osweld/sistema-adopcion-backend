package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.services.EstadoSolicitudAdopcionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estado-solicitud-adopcion")
public class EstadoSolicitudAdopcionController {

    private final EstadoSolicitudAdopcionService estadoSolicitudAdopcionService;

    public EstadoSolicitudAdopcionController(EstadoSolicitudAdopcionService estadoSolicitudAdopcionService) {
        this.estadoSolicitudAdopcionService = estadoSolicitudAdopcionService;
    }


    @GetMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PermitAll()
    ResponseEntity<List<EstadoSolicitudAdopcion>> getAllEstadoSolicitudAdopcion(){
        return new ResponseEntity<>(estadoSolicitudAdopcionService.getAllEstadoSolicitudAdopcion(), HttpStatus.OK);
    }

    @GetMapping("/{idEstadoSolicitudAdopcion}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoSolicitudAdopcion> getAllEstadoSolicitudAdopcion(@PathVariable Long idEstadoSolicitudAdopcion){
        return new ResponseEntity<>(estadoSolicitudAdopcionService.getEstadoSolicitudAdopcionById(idEstadoSolicitudAdopcion), HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoSolicitudAdopcion> createEstadoSolicitudAdopcion(@Valid @RequestBody EstadoSolicitudAdopcion estadoSolicitudAdopcion){
        return new ResponseEntity<>(estadoSolicitudAdopcionService.createEstadoSolicitudAdopcion(estadoSolicitudAdopcion),HttpStatus.CREATED);
    }

    @PutMapping("/{idEstadoSolicitudAdopcion}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoSolicitudAdopcion> editEstadoSolicitudAdopcion(@PathVariable Long idEstadoSolicitudAdopcion, @Valid @RequestBody EstadoSolicitudAdopcion estadoSolicitudAdopcion){
        return new ResponseEntity<>(estadoSolicitudAdopcionService.editEstadoSolicitudAdopcion(idEstadoSolicitudAdopcion,estadoSolicitudAdopcion),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEstadoSolicitudAdopcion}")
     @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoSolicitudAdopcion> deleteEstadoSolicitudAdopcionById(@PathVariable Long idEstadoSolicitudAdopcion){
        return new ResponseEntity<>(estadoSolicitudAdopcionService.deleteEstadoSolicitudAdopcionById(idEstadoSolicitudAdopcion),HttpStatus.OK);
    }
}
