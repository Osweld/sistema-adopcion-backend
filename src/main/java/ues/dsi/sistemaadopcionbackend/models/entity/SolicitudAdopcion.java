package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "solicitud_adopcion")
@Getter
@Setter
@NoArgsConstructor
public class SolicitudAdopcion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El motivo de solicitud no puede estar vacío")
    @Size(min = 1, max = 150, message = "El motivo de solicitud debe tener entre 1 y 150 caracteres")
    @Column(name = "motivo_solicitud", length = 150, nullable = false)
    private String motivo;

    @Column(name = "descripcion", length = 250, nullable = true)
    private String descripcion;

    @Column(name = "comentario_gestion_solicitud", length = 200, nullable = true)
    private String comentarioGestionSolicitud;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota mascota;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false )
    @JoinColumn(name = "id_estado_solicitud_adopcion", nullable = false)
    private EstadoSolicitudAdopcion estadoSolicitudAdopcion;

    public SolicitudAdopcion(Long id) {
        this.id = id;
    }
}
