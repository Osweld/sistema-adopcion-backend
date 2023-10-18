package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.DetalleSolicitudAdopcion;
import ues.dsi.sistemaadopcionbackend.services.DetalleSolicitudAdopcionService;

@RestController
@RequestMapping("/api/v1/detalleSolicitud-adopcion")
public class DetalleSolicitudAdopcionController {

    private final DetalleSolicitudAdopcionService detalleSolicitudAdopcionService;

    public DetalleSolicitudAdopcionController(DetalleSolicitudAdopcionService detalleSolicitudAdopcionService) {
        this.detalleSolicitudAdopcionService = detalleSolicitudAdopcionService;
    }

    @GetMapping()
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<DetalleSolicitudAdopcion>> getAllDetalleSolicitudesAdopcion(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                                    @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(detalleSolicitudAdopcionService.getAllDetalleSolicitudesAdopcion(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{idDetalleSolicitudAdopcion}")
    @PermitAll()
    ResponseEntity<DetalleSolicitudAdopcion> getDetalleSolicitudAdopcionById(@PathVariable Long idDetalleSolicitudAdopcion){
        return new ResponseEntity<>(detalleSolicitudAdopcionService.getDetalleSolicitudAdopcionById(idDetalleSolicitudAdopcion),HttpStatus.OK);
    }

    @PostMapping("")
    @PermitAll()
    ResponseEntity<DetalleSolicitudAdopcion> createDetalleSolicitudAdopcion(@Valid @RequestBody DetalleSolicitudAdopcion detalleSolicitudAdopcion){
        return new ResponseEntity<>(detalleSolicitudAdopcionService.createDetalleSolicitudAdopcion(detalleSolicitudAdopcion),HttpStatus.CREATED);
    }

    @PutMapping("/{idDetalleSolicitudAdopcion}")
    @PermitAll()
    ResponseEntity<DetalleSolicitudAdopcion> editDetalleSolicitudAdopcion(@PathVariable Long idDetalleSolicitudAdopcion,@Valid @RequestBody DetalleSolicitudAdopcion detalleSolicitudAdopcion){
        return new ResponseEntity<>(detalleSolicitudAdopcionService.editDetalleSolicitudAdopcion(idDetalleSolicitudAdopcion,detalleSolicitudAdopcion),HttpStatus.OK);
    }

    @DeleteMapping("/{idDetalleSolicitudAdopcion}")
    @PermitAll()
    ResponseEntity<DetalleSolicitudAdopcion> deleteDetalleSolicitudAdopcion(@PathVariable Long idDetalleSolicitudAdopcion){
        return new ResponseEntity<>(detalleSolicitudAdopcionService.deleteDetalleSolicitudAdopcionById(idDetalleSolicitudAdopcion),HttpStatus.OK);
    }


}
