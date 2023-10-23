package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "estado_cita_solicitud")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCitaSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(min = 1, max = 20, message = "El estado debe tener entre 1 y 20 caracteres")
    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    public EstadoCitaSolicitud(long id) {
        this.id = id;
    }
}

