package ues.dsi.sistemaadopcionbackend.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 40, message = "El nombre debe tener entre 1 y 40 caracteres")
    @Column(name = "nombres", length = 40, nullable = false)
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacío")
    @Size(min = 1, max = 40, message = "Los apellidos debe tener entre 1 y 40 caracteres")
    @Column(name = "apellidos", length = 40, nullable = false)
    private String apellidos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "########-#")
    @Size(min = 1, max = 10, message = "El color debe tener entre 1 y 10 caracteres (Incluyendo el guión)")
    @Column(name = "num_dui", length = 10, nullable = false)
    private String numeroDui;

    @Size(min = 1, max = 150, message = "La dirección debe tener entre 1 y 150 caracteres")
    @Column(name = "direccion", length = 150, nullable = true)
    private String direccion;

    @Size(min = 1, max = 40, message = "El email debe tener entre 1 y 40 caracteres")
    @Column(name = "email", length = 40, nullable = true)
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "####-####")
    @NotBlank(message = "El número de telefono no puede estar vacío")
    @Size(min = 8, max = 9, message = "El número de telefono debe tener almenos 8 caracteres")
    @Column(name = "telefono", length = 9, nullable = false)
    private String telefono;

    @NotBlank(message = "El username no puede estar vacío")
    @Size(min = 5, max = 15, message = "El username debe tener entre 5 y 15 caracteres")
    @Column(name = "username", length = 15, nullable = false)
    private String username;

    @NotBlank(message = "El password no puede estar vacío")
    @Size(min = 8, max = 60, message = "El password debe tener entre 8 y 60 caracteres")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_genero")
    private Genero genero;

    @ManyToOne(optional = false )
    @JoinColumn(name = "id_rol" )
    private Rol rol;

    public Usuario(Long id) {
        this.id = id;
    }
}
