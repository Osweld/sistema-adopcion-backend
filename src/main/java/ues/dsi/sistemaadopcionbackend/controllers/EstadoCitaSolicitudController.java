package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoCitaSolicitud;
import ues.dsi.sistemaadopcionbackend.services.EstadoCitaSolicitudService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estado-cita-solicitud-adopcion")
public class EstadoCitaSolicitudController {

    private final EstadoCitaSolicitudService estadoCitaSolicitudService;

    public EstadoCitaSolicitudController(EstadoCitaSolicitudService estadoCitaSolicitudService) {
        this.estadoCitaSolicitudService = estadoCitaSolicitudService;
    }


    @GetMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<List<EstadoCitaSolicitud>> getAllEstadoCitaSolicituds(){
        return new ResponseEntity<>(estadoCitaSolicitudService.getAllEstadoCitaSolicitud(), HttpStatus.OK);
    }

    @GetMapping("/{idEstadoCitaSolicitud}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoCitaSolicitud> getAllEstadoCitaSolicituds(@PathVariable Long idEstadoCitaSolicitud){
        return new ResponseEntity<>(estadoCitaSolicitudService.getEstadoCitaSolicitudById(idEstadoCitaSolicitud), HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoCitaSolicitud> createEstadoCitaSolicitud(@Valid @RequestBody EstadoCitaSolicitud estadoCitaSolicitud){
        return new ResponseEntity<>(estadoCitaSolicitudService.createEstadoCitaSolicitud(estadoCitaSolicitud),HttpStatus.CREATED);
    }

    @PutMapping("/{idEstadoCitaSolicitud}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoCitaSolicitud> editEstadoCitaSolicitud(@PathVariable Long idEstadoCitaSolicitud, @Valid @RequestBody EstadoCitaSolicitud estadoCitaSolicitud){
        return new ResponseEntity<>(estadoCitaSolicitudService.editEstadoCitaSolicitud(idEstadoCitaSolicitud,estadoCitaSolicitud),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEstadoCitaSolicitud}")
     @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<EstadoCitaSolicitud> deleteEstadoCitaSolicitudById(@PathVariable Long idEstadoCitaSolicitud){
        return new ResponseEntity<>(estadoCitaSolicitudService.deleteEstadoCitaSolicitudById(idEstadoCitaSolicitud),HttpStatus.OK);
    }
}
