package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "horas_cita_solicitud")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraCitaSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La hora de cita no puede estar vacío")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Column(name = "hora_cita")
    private Time horaCita;

    public HoraCitaSolicitud(long id) {
        this.id = id;
    }
}

