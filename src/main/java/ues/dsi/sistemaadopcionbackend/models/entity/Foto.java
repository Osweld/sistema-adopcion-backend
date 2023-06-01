package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name ="fotos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link", length = 200, nullable = false)
    private String link;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota mascota;

}
