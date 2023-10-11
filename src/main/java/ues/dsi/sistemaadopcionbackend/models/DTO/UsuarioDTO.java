package ues.dsi.sistemaadopcionbackend.models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ues.dsi.sistemaadopcionbackend.models.entity.Genero;
import ues.dsi.sistemaadopcionbackend.models.entity.Rol;

import java.io.Serializable;
import java.util.Date;

@Data
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 40, message = "El nombre debe tener entre 1 y 40 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacío")
    @Size(min = 1, max = 40, message = "Los apellidos debe tener entre 1 y 40 caracteres")
    private String apellidos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "########-#")
    @Size(min = 1, max = 10, message = "El color debe tener entre 1 y 10 caracteres (Incluyendo el guión)")
    private String numeroDui;

    @Size(min = 1, max = 150, message = "La dirección debe tener entre 1 y 150 caracteres")
    private String direccion;

    @Size(min = 1, max = 40, message = "El email debe tener entre 1 y 40 caracteres")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "####-####")
    @NotBlank(message = "El número de telefono no puede estar vacío")
    @Size(min = 8, max = 9, message = "El número de telefono debe tener almenos 8 caracteres")
    private String telefono;

    @NotBlank(message = "El username no puede estar vacío")
    @Size(min = 5, max = 15, message = "El username debe tener entre 5 y 15 caracteres")
    private String username;

    private Genero genero;

    private Rol rol;

}
