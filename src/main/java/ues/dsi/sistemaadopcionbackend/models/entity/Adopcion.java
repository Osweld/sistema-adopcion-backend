package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "adopciones")
@Getter
@Setter
@NoArgsConstructor
public class Adopcion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false )
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota Mascota;

    public Adopcion(Long id) {
        this.id = id;
    }
}
