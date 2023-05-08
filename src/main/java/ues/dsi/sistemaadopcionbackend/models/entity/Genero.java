package ues.dsi.sistemaadopcionbackend.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "generos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;

}
