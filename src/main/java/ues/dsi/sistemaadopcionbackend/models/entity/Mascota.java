package ues.dsi.sistemaadopcionbackend.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mascotas")
@Getter
@Setter
@NoArgsConstructor
public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 40, message = "El nombre debe tener entre 1 y 40 caracteres")
    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Size(min = 1, max = 60, message = "El color debe tener entre 1 y 60 caracteres")
    @Column(name = "color", length = 60)
    private String color;

    @Size(min = 1, max = 250, message = "La descripción debe tener entre 1 y 250 caracteres")
    @Column(name = "descripcion", length = 250, nullable = true)
    private String descripcion;


    @Column(name = "foto_principal", length = 200, nullable = true)
    private String fotoPrincipal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @ManyToOne(optional = false )
    @JoinColumn(name = "id_raza", nullable = false)
    private Raza raza;

    @ManyToOne(optional = false )
    @JoinColumn(name = "id_estado_salud", nullable = false)
    private EstadoSalud estadoSalud;

    @ManyToOne(optional = false )
    @JoinColumn(name = "id_estado_mascota", nullable = false)
    private EstadoMascota estadoMascota;

    public Mascota(Long id) {
        this.id = id;
    }
}
