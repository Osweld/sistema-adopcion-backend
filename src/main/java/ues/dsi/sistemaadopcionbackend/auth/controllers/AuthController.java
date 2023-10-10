package ues.dsi.sistemaadopcionbackend.auth.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ues.dsi.sistemaadopcionbackend.auth.models.AuthUser;
import ues.dsi.sistemaadopcionbackend.auth.services.JWTService;
import ues.dsi.sistemaadopcionbackend.auth.services.JWTServiceImpl;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.services.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JWTService jwtService;
    private final UsuarioService usuarioService;


    public AuthController(JWTService jwtService, UsuarioService userService) {
        this.jwtService = jwtService;
        this.usuarioService = userService;
    }

    @GetMapping("/token/refresh")
    @PermitAll()
    ResponseEntity<Map<String,Object>> refreshToken(HttpServletRequest request) throws IOException {
        Map<String,Object> body = new HashMap<>();
        String header = request.getHeader(JWTServiceImpl.HEADER_STRING);
        if(!jwtService.requiresAuthentication(header) || !jwtService.validate(header)){
            body.put("error","there was an error refreshing token");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);

        }
        System.out.println("header: "+header);
        System.out.println("id: "+jwtService.getId(header));
        System.out.println("id: "+jwtService.getUsername(header));
        System.out.println("id: "+jwtService.getAuthorities(header));



        AuthUser user = new AuthUser( jwtService.getId(header),jwtService.getUsername(header),"",true,true,true,true,jwtService.getAuthorities(header));
        String token = jwtService.refreshToken(user);
        body.put("user",user);
        body.put("token",token);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @PostMapping("register")
    @PermitAll()
    ResponseEntity<Usuario> registerUsuario(@Valid @RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.registerUsuario(usuario),HttpStatus.CREATED);
    }
}
