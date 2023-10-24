package ues.dsi.sistemaadopcionbackend.controllers;


import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.CitaSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.services.CitaSolicitudAdopcionService;

@RestController
@RequestMapping("/api/v1/cita-solicitud-adopcion")
public class CitaSolicitudAdopcionController {

    private final CitaSolicitudAdopcionService citaSolicitudAdopcionService;


    public CitaSolicitudAdopcionController(CitaSolicitudAdopcionService citaSolicitudAdopcionService) {
        this.citaSolicitudAdopcionService = citaSolicitudAdopcionService;
    }

    @GetMapping("")
    //@Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @PermitAll()
    ResponseEntity<Page<CitaSolicitudAdopcion>> getAllCitaSolicitudAdopcionWithPagination(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                               @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(citaSolicitudAdopcionService.getAllCitasSolicitudAdopcion(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/fecha-cita/{fechaCita}")
    @PermitAll()
    ResponseEntity<List<CitaSolicitudAdopcion>> getAllByFechaCita(@PathVariable Date fechaCita) {
            return new ResponseEntity<>(citaSolicitudAdopcionService.getAllByFechaCita(fechaCita), HttpStatus.OK);
    }

    @GetMapping("/estado-cita-solicitud/{idEstadoCitaSolicitud}")
    @PermitAll()
    ResponseEntity<List<CitaSolicitudAdopcion>> getAllByEstadoCitaSolicitudId(@PathVariable Long idEstadoCitaSolicitud) {
            return new ResponseEntity<>(citaSolicitudAdopcionService.getAllByEstadoCitaSolicitudId(idEstadoCitaSolicitud), HttpStatus.OK);
    }

    @GetMapping("/solicitud-adopcion/{idSolicitudAdopcion}")
    @PermitAll()
    ResponseEntity<List<CitaSolicitudAdopcion>> getBySolicitudAdopcionId(@PathVariable Long idSolicitudAdopcion) {
            return new ResponseEntity<>(citaSolicitudAdopcionService.getBySolicitudAdopcionId(idSolicitudAdopcion), HttpStatus.OK);
    }

    @GetMapping("/{idCitaSolicitudAdopcion}")
    @PermitAll()
    ResponseEntity<CitaSolicitudAdopcion> getCitaSolicitudAdopcionById(@PathVariable Long idCitaSolicitudAdopcion){
        return new ResponseEntity<>(citaSolicitudAdopcionService.getCitaSolicitudAdopcionById(idCitaSolicitudAdopcion),HttpStatus.OK);
    }

    @GetMapping("/existsfechahora-cita/{fechaCita}/{idHoraCita}")
    @PermitAll()
    ResponseEntity<Boolean> getExistsCitaSolicitudAdopcionByFechaCitaAndHoraCitaSolicitudId(@PathVariable String fechaCita, @PathVariable Long idHoraCita) {
        return new ResponseEntity<>(citaSolicitudAdopcionService.getExistsCitaSolicitudAdopcionByFechaCitaAndHoraCitaSolicitudId(fechaCita, idHoraCita), HttpStatus.OK);
    }

    @GetMapping("/existshoracita/{idHoraCitaSolicitud}")
    @PermitAll()
    ResponseEntity<Boolean> getExistsCitaSolicitudAdopcionByHoraCitaSolicitudId(@PathVariable Long idHoraCitaSolicitud) {
        return new ResponseEntity<>(citaSolicitudAdopcionService.getExistsCitaSolicitudAdopcionByHoraCitaSolicitudId(idHoraCitaSolicitud), HttpStatus.OK);
    }

    @GetMapping("/existsfechacita/{fechaCita}")
    @PermitAll()
    ResponseEntity<Boolean> getExistsCitaSolicitudAdopcionByFechaCita(@PathVariable String fechaCita) {
        return new ResponseEntity<>(citaSolicitudAdopcionService.getExistsCitaSolicitudAdopcionByFechaCita(fechaCita), HttpStatus.OK);
    }

    @PostMapping("")
    @PermitAll()
    ResponseEntity<CitaSolicitudAdopcion> createCitaSolicitudAdopcion(@Valid @RequestBody CitaSolicitudAdopcion citaSolicitudAdopcion){
        return new ResponseEntity<>(citaSolicitudAdopcionService.createCitaSolicitudAdopcion(citaSolicitudAdopcion),HttpStatus.CREATED);
    }


    @PutMapping("/{idCitaSolicitudAdopcion}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<CitaSolicitudAdopcion> editCitaSolicitudAdopcion(@PathVariable Long idCitaSolicitudAdopcion,
                                        @Valid @RequestBody CitaSolicitudAdopcion citaSolicitudAdopcion){
        return new ResponseEntity<>(citaSolicitudAdopcionService.editCitaSolicitudAdopcion(idCitaSolicitudAdopcion,citaSolicitudAdopcion),HttpStatus.OK);
    }

    @DeleteMapping("/{idCitaSolicitudAdopcion}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<CitaSolicitudAdopcion> deleteCitaSolicitudAdopcion(@PathVariable Long idCitaSolicitudAdopcion){
        return new ResponseEntity<>(citaSolicitudAdopcionService.deleteCitaSolicitudAdopcionById(idCitaSolicitudAdopcion),HttpStatus.OK);
    }


}
