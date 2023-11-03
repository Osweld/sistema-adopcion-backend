package ues.dsi.sistemaadopcionbackend.models.DTO;

import lombok.Data;

@Data
public class SimpleEmail {
    private String to;
    private String subject;
    private String body;


}