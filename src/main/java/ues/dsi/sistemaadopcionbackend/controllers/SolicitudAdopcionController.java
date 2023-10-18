package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.services.SolicitudAdopcionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitud-adopcion")
public class SolicitudAdopcionController {

    private final SolicitudAdopcionService solicitudAdopcionService;

    public SolicitudAdopcionController(SolicitudAdopcionService solicitudAdopcionService) {
        this.solicitudAdopcionService = solicitudAdopcionService;
    }

    @GetMapping()
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<SolicitudAdopcion>> getAllSolicitudesAdopcion(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                    @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(solicitudAdopcionService.getAllSolicitudesAdopcion(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/mascota/{idMascota}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<SolicitudAdopcion>> getSolicitudesAdopcionByMascotaId(@PathVariable Long idMascota, 
                                                                        @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                        @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(solicitudAdopcionService.getAllSolicitudesAdopcionByMascotaId(idMascota, PageRequest.of(page, size)),HttpStatus.OK);
    }

    @GetMapping("/estado-solicitud-adopcion/{idEstadoSolicitudAdopcion}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<SolicitudAdopcion>> getSolicitudesAdopcionByEstadoSolicitudesAdopcionId(@PathVariable Long idEstadoSolicitudAdopcion, 
                                                                        @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                        @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(solicitudAdopcionService.getAllSolicitudesAdopcionByEstadoSolicitudAdopcion(idEstadoSolicitudAdopcion, PageRequest.of(page, size)),HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    @PermitAll()
    ResponseEntity<Page<SolicitudAdopcion>> getSolicitudesAdopcionByUsuarioId(@PathVariable Long idUsuario, 
                                                                        @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                        @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(solicitudAdopcionService.getAllSolicitudesAdopcionByUsuarioId(idUsuario, PageRequest.of(page, size)),HttpStatus.OK);
    }

    @GetMapping("/{idSolicitudAdopcion}")
    @PermitAll()
    ResponseEntity<SolicitudAdopcion> getSolicitudAdopcionById(@PathVariable Long idSolicitudAdopcion){
        return new ResponseEntity<>(solicitudAdopcionService.getSolicitudAdopcionById(idSolicitudAdopcion),HttpStatus.OK);
    }

    @PostMapping("")
    @PermitAll()
    ResponseEntity<SolicitudAdopcion> createSolicitudAdopcion(@Valid @RequestBody SolicitudAdopcion solicitudAdopcion){
        return new ResponseEntity<>(solicitudAdopcionService.createSolicitudAdopcion(solicitudAdopcion),HttpStatus.CREATED);
    }

    @PutMapping("/{idSolicitudAdopcion}")
    @PermitAll()
    ResponseEntity<SolicitudAdopcion> editSolicitudAdopcion(@PathVariable Long idSolicitudAdopcion,@Valid @RequestBody SolicitudAdopcion solicitudAdopcion){
        return new ResponseEntity<>(solicitudAdopcionService.editSolicitudAdopcion(idSolicitudAdopcion,solicitudAdopcion),HttpStatus.OK);
    }

    @DeleteMapping("/{idSolicitudAdopcion}")
    @PermitAll()
    ResponseEntity<SolicitudAdopcion> deleteSolicitudAdopcion(@PathVariable Long idSolicitudAdopcion){
        return new ResponseEntity<>(solicitudAdopcionService.deleteSolicitudAdopcionById(idSolicitudAdopcion),HttpStatus.OK);
    }


}
