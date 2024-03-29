package ues.dsi.sistemaadopcionbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.security.PermitAll;
import ues.dsi.sistemaadopcionbackend.models.DTO.Message;
import ues.dsi.sistemaadopcionbackend.models.DTO.UsuarioDTO;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;
import ues.dsi.sistemaadopcionbackend.services.EmailService;
import ues.dsi.sistemaadopcionbackend.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/password-reset")
public class PasswordResetController {

    private final UsuarioService usuarioService;
    private final EmailService emailService;

    public PasswordResetController(UsuarioService usuarioService, EmailService emailService) {
        this.usuarioService = usuarioService;
        this.emailService = emailService;
    }

    @GetMapping("/request")
    @PermitAll()
    public ResponseEntity<?> requestPasswordReset(@RequestParam (name = "email", defaultValue = "",required = true) String email) {
        Usuario usuario = usuarioService.getUsuarioByEmail(email);
        
        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuario no existe o no ha podido ser encontrado "+ email);
        }
        String passwordTemporal = emailService.generateTemporaryPassword();

        usuario.setPassword(passwordTemporal);
        usuarioService.editPasswordUsuario(usuario);

        // Enviar el correo electrónico
        emailService.sendPasswordResetEmail(usuario.getEmail(), passwordTemporal);


        return ResponseEntity.ok(new Message("Contraseña restablecida con éxito, puede verificar en su correo electrónico"));
    }

    

}
