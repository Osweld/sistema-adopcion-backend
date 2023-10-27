package ues.dsi.sistemaadopcionbackend.models.DTO;

import lombok.Data;

@Data
public class VerificarSolicitudDTO {

    private Long idSolicitud;
    private Long idEstadoSolicitud;
    private String comentarios;

}
