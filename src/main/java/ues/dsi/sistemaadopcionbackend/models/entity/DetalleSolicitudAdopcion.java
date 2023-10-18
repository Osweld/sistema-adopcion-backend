package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "detalle_solicitud_adopcion")
@Getter
@Setter
@NoArgsConstructor
public class DetalleSolicitudAdopcion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El apartado de número de integrantes del hogar no puede estar vacío")
    @Column(name = "num_integrantes_hogar", nullable = false)
    private Integer numIntegrantesHogar;

    @NotBlank(message = "El apartado para casa Propia no pueden estar vacío")
    @Size(min = 1, max = 10, message = "El apartado para casa Propia debe tener entre 1 y 10 caracteres")
    @Column(name = "casa_propia", length = 10, nullable = false)
    private String casaPropia;

    @NotBlank(message = "El apartado para zona de la vivienda no pueden estar vacío")
    @Size(min = 1, max = 10, message = "El aparatado para zona de la vivienda debe tener entre 1 y 10 caracteres")
    @Column(name = "zona_vivienda", length = 10, nullable = false)
    private String zonaVivienda;

    @NotBlank(message = "El apartado de si posee patio no pueden estar vacío")
    @Size(min = 1, max = 10, message = "El apartado de si posee patio debe tener entre 1 y 10 caracteres")
    @Column(name = "posee_patio", length = 10, nullable = false)
    private String poseePatio;

    @NotBlank(message = "El apartado de si tiene niños no pueden estar vacío")
    @Size(min = 1, max = 10, message = "El apartado de si tiene niños debe tener entre 1 y 10 caracteres")
    @Column(name = "tiene_ninos", length = 10, nullable = false)
    private String tieneNinos;

    @NotBlank(message = "El apartado de si tiene mascotas no pueden estar vacío")
    @Size(min = 1, max = 10, message = "El apartado de si tiene mascotas debe tener entre 1 y 10 caracteres")
    @Column(name = "tiene_mascotas", length = 10, nullable = false)
    private String tieneMascotas;

    @Column(name = "detalle_mascotas_posee", length = 250, nullable = true)
    private String detalleMascotasPosee;

    @NotBlank(message = "El apartado de si tuvo mascotas no pueden estar vacío")
    @Size(min = 1, max = 10, message = "El apartado de si tuvo mascotas debe tener entre 1 y 10 caracteres")
    @Column(name = "tuvo_mascotas", length = 10, nullable = false)
    private String tuvoMascotas;

    @Column(name = "detalle_mascotas_tuvo", length = 250, nullable = true)
    private String detalleMascotasTuvo;

    @Size(min = 1, max = 150, message = "El apartado de si dispone tiempo al dia debe tener entre 1 y 150 caracteres")
    @Column(name = "dispone_tiempo_al_dia", length = 150, nullable = true)
    private String disponeTiempoAlDia;

    public DetalleSolicitudAdopcion(Long id) {
        this.id = id;
    }
}
