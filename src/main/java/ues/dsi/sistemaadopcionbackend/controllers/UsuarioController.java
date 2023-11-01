package ues.dsi.sistemaadopcionbackend.controllers;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.models.DTO.ChangePasswordDTO;
import ues.dsi.sistemaadopcionbackend.models.DTO.UsuarioDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.services.UsuarioService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<Usuario>> getAllUsuariosWithPagination(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                               @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(usuarioService.getAllUsuarios(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/rol/{idRol}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<Usuario>> getUsuariosByRolWithPagination(@PathVariable Long idRol,
                                                                  @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                  @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(usuarioService.getAllUsuariosByRol(idRol,PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/genero/{idGenero}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<Usuario>> getUsuariosByGeneroWithPagination(@PathVariable Long idGenero,
                                                                     @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                                     @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return new ResponseEntity<>(usuarioService.getAllUsuariosByGenero(idGenero,PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/searchname/{keywords}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Page<Usuario>> searchUsuarioByNombresWithPagination(@PathVariable String keywords,
                                                         @RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                                         @RequestParam(name = "size",defaultValue = "10",required = false) int size) {
        return new ResponseEntity<>(usuarioService.searchUsuarioByNombres(keywords,PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    ResponseEntity<Usuario> getUsuarioById(@PathVariable Long idUsuario, Principal principal){
        return new ResponseEntity<>(usuarioService.getUsuarioById(idUsuario,principal),HttpStatus.OK);
    }

    @GetMapping("/identidad/{numDui}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Usuario> findByNoIdentidad(@PathVariable String numDui) {
            return new ResponseEntity<>(usuarioService.getUsuarioByNumeroDui(numDui), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    @PermitAll()
    ResponseEntity<Usuario> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(usuarioService.getUsuarioByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @PermitAll()
    ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
        return new ResponseEntity<>(usuarioService.getUsuarioByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/existsusername/{username}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Boolean> getExistsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(usuarioService.getExistsUsuarioByUsername(username), HttpStatus.OK);
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.createUsuario(usuario),HttpStatus.CREATED);
    }


    @PutMapping("/{idUsuario}")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    ResponseEntity<Usuario> editUsuario(@PathVariable Long idUsuario,
                                        @Valid @RequestBody UsuarioDTO usuario){
        return new ResponseEntity<>(usuarioService.editUsuario(idUsuario,usuario),HttpStatus.OK);
    }

    @PutMapping("/change-password")
    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_USER"})
    ResponseEntity<Usuario> changePassword(
                                        @Valid @RequestBody ChangePasswordDTO changePasswordDTO,Principal principal){
        return new ResponseEntity<>(usuarioService.changePassword(changePasswordDTO,principal),HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    @Secured("ROLE_ADMIN")//seria desactivar usuario
    ResponseEntity<Usuario> deleteUsuario(@PathVariable Long idUsuario){
        return new ResponseEntity<>(usuarioService.deleteUsuarioById(idUsuario),HttpStatus.OK);
    }


}
