package ues.dsi.sistemaadopcionbackend.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "cita_solicitud_adopcion")
@Getter
@Setter
@NoArgsConstructor
public class CitaSolicitudAdopcion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "fecha_cita")
    private Date fechaCita;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Column(name = "hora_cita")
    private Time horaCita;

    @NotBlank(message = "Los motivos de la cita no pueden estar vacíos")
    @Size(min = 1, max = 150, message = "Los motivos de la cita deben tener entre 1 y 150 caracteres")
    @Column(name = "motivo_cita", length = 150, nullable = false)
    private String motivo;

    @Column(name = "descripcion", length = 250, nullable = true)
    private String descripcion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_solicitud_adopcion", nullable = false)
    private SolicitudAdopcion solicitudAdopcion;

    @ManyToOne(optional = false )
    @JoinColumn(name = "id_estado_cita_solicitud", nullable = false)
    private EstadoCitaSolicitud estadoCitaSolicitud;

    public CitaSolicitudAdopcion(Long id) {
        this.id = id;
    }
}
