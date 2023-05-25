package ues.dsi.sistemaadopcionbackend.controllers;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.EstadoSalud;
import ues.dsi.sistemaadopcionbackend.services.EstadoSaludService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estado-salud")
public class EstadoSaludController {

    private final EstadoSaludService estadoSaludService;

    public EstadoSaludController(EstadoSaludService estadoSaludService) {
        this.estadoSaludService = estadoSaludService;
    }


    @GetMapping("")
    ResponseEntity<List<EstadoSalud>> getAllEstadoSaluds(){
        return new ResponseEntity<>(estadoSaludService.getAllEstadoSalud(), HttpStatus.OK);
    }

    @GetMapping("/{idEstadoSalud}")
    ResponseEntity<EstadoSalud> getAllEstadoSaluds(@PathVariable Long idEstadoSalud){
        return new ResponseEntity<>(estadoSaludService.getEstadoSaludById(idEstadoSalud), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<EstadoSalud> createEstadoSalud(@Valid @RequestBody EstadoSalud estadoSalud){
        return new ResponseEntity<>(estadoSaludService.createEstadoSalud(estadoSalud),HttpStatus.CREATED);
    }

    @PutMapping("/{idEstadoSalud}")
    ResponseEntity<EstadoSalud> editEstadoSalud(@PathVariable Long idEstadoSalud, @Valid @RequestBody EstadoSalud estadoSalud){
        return new ResponseEntity<>(estadoSaludService.editEstadoSalud(idEstadoSalud,estadoSalud),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idEstadoSalud}")
    ResponseEntity<EstadoSalud> deleteEstadoSaludById(@PathVariable Long idEstadoSalud){
        return new ResponseEntity<>(estadoSaludService.deleteEstadoSaludById(idEstadoSalud),HttpStatus.OK);
    }
}
