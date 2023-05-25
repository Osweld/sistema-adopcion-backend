package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name ="estado_salud")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoSalud implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El estado de salud no puede estar vacío")
    @Size(min = 1, max = 50, message = "El estado de salud debe tener entre 1 y 50 caracteres")
    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

}
