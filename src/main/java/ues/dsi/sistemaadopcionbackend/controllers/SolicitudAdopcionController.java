package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.DTO.CreateSolicitudAdopcionDTO;
import ues.dsi.sistemaadopcionbackend.models.DTO.VerificarSolicitudDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.SolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.services.SolicitudAdopcionService;

import java.security.Principal;

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
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    ResponseEntity<Page<SolicitudAdopcion>> getSolicitudesAdopcionByUsuarioId(@PathVariable Long idUsuario, 
                                                                        @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                        @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(solicitudAdopcionService.getAllSolicitudesAdopcionByUsuarioId(idUsuario, PageRequest.of(page, size)),HttpStatus.OK);
    }

    @GetMapping("/{idSolicitudAdopcion}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    ResponseEntity<SolicitudAdopcion> getSolicitudAdopcionById(@PathVariable Long idSolicitudAdopcion){
        return new ResponseEntity<>(solicitudAdopcionService.getSolicitudAdopcionById(idSolicitudAdopcion),HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    ResponseEntity<SolicitudAdopcion> createSolicitudAdopcion(@Valid @RequestBody CreateSolicitudAdopcionDTO createSolicitudAdopcionDTO, Principal principal){
        return new ResponseEntity<>(solicitudAdopcionService.createSolicitudAdopcion(createSolicitudAdopcionDTO,principal),HttpStatus.CREATED);
    }

    @PutMapping("/verificar")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<SolicitudAdopcion> verificarSolicitudAdopcion(@Valid @RequestBody VerificarSolicitudDTO verificarSolicitudDTO){
        return new ResponseEntity<>(solicitudAdopcionService.verificarSolicitudAdopcion(verificarSolicitudDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{idSolicitudAdopcion}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<SolicitudAdopcion> deleteSolicitudAdopcion(@PathVariable Long idSolicitudAdopcion){
        return new ResponseEntity<>(solicitudAdopcionService.deleteSolicitudAdopcionById(idSolicitudAdopcion),HttpStatus.OK);
    }

    @GetMapping("/proceso")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    ResponseEntity<SolicitudAdopcion> getSolicitudAdopcionByUsuarioAndEstadoEnProceso(Principal principal){
        return new ResponseEntity<>(solicitudAdopcionService.getSolicitudByUsuarioAndEstadoSolicitud(principal),HttpStatus.OK);
    }

    @GetMapping("/rechazadas")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    ResponseEntity<Page<SolicitudAdopcion>> getSolicitudesAdopcionByEstadoSolicitudesAdopcionAndUsuario(Principal principal,
                                                                                                @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                                                @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(solicitudAdopcionService.getSolicitudAdopcionByUsuarioAndEStadoAdopcion(principal, PageRequest.of(page, size)),HttpStatus.OK);
    }



}
