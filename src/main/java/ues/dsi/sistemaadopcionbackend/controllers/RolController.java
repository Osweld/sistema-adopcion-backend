package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.Rol;
import ues.dsi.sistemaadopcionbackend.services.RolService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }


    @GetMapping("")
    ResponseEntity<List<Rol>> getAllRoles(){
        return new ResponseEntity<>(rolService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{idRol}")
    ResponseEntity<Rol> getAllRoles(@PathVariable Long idRol){
        return new ResponseEntity<>(rolService.getRolById(idRol), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Rol> createRol(@Valid @RequestBody Rol rol){
        return new ResponseEntity<>(rolService.createRol(rol),HttpStatus.CREATED);
    }

    @PutMapping("/{idRol}")
    ResponseEntity<Rol> editRol(@PathVariable Long idRol, @Valid @RequestBody Rol rol){
        return new ResponseEntity<>(rolService.editRol(idRol,rol),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idRol}")
    ResponseEntity<Rol> deleteRolById(@PathVariable Long idRol){
        return new ResponseEntity<>(rolService.deleteRolById(idRol),HttpStatus.OK);
    }

}
