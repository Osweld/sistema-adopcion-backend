package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "razas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Raza implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 40, message = "El nombre debe tener entre 1 y 40 caracteres")
    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;
}
