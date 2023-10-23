package ues.dsi.sistemaadopcionbackend.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.entity.HoraCitaSolicitud;
import ues.dsi.sistemaadopcionbackend.services.HoraCitaSolicitudService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hora-cita-solicitud")
public class HoraCitaSolicitudController {

    private final HoraCitaSolicitudService horaCitaSolicitudService;

    public HoraCitaSolicitudController(HoraCitaSolicitudService horaCitaSolicitudService) {
        this.horaCitaSolicitudService = horaCitaSolicitudService;
    }

    @GetMapping("")
    @PermitAll()
    ResponseEntity<List<HoraCitaSolicitud>> getAllHorasCitaSolicitud(){
        return new ResponseEntity<>(horaCitaSolicitudService.getAllHorasCitaSolicitud(), HttpStatus.OK);
    }

    @GetMapping("/{idHoraCitaSolicitud}")
    @PermitAll()
    ResponseEntity<HoraCitaSolicitud> getHoraCitaSolicitudById(@PathVariable Long idHoraCitaSolicitud){
        return new ResponseEntity<>(horaCitaSolicitudService.getHoraCitaSolicitudById(idHoraCitaSolicitud), HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<HoraCitaSolicitud> createHoraCitaSolicitud(@Valid @RequestBody HoraCitaSolicitud horaCitaSolicitud){
        return new ResponseEntity<>(horaCitaSolicitudService.createHoraCitaSolicitud(horaCitaSolicitud),HttpStatus.CREATED);
    }

    @PutMapping("/{idHoraCitaSolicitud}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<HoraCitaSolicitud> editHoraCitaSolicitud(@PathVariable Long idHoraCitaSolicitud, @Valid @RequestBody HoraCitaSolicitud horaCitaSolicitud){
        return new ResponseEntity<>(horaCitaSolicitudService.editHoraCitaSolicitud(idHoraCitaSolicitud,horaCitaSolicitud),HttpStatus.CREATED);
    }

    @DeleteMapping("/{idHoraCitaSolicitud}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<HoraCitaSolicitud> deleteHoraCitaSolicitudById(@PathVariable Long idHoraCitaSolicitud){
        return new ResponseEntity<>(horaCitaSolicitudService.deleteHoraCitaSolicitudById(idHoraCitaSolicitud),HttpStatus.OK);
    }
}
