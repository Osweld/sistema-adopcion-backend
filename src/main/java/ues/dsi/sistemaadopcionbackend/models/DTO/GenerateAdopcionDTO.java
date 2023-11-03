package ues.dsi.sistemaadopcionbackend.models.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GenerateAdopcionDTO {

    @NotNull
    private Long idUsuario;
    @NotNull
    private Long idMascota;

}
