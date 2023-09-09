package ues.dsi.sistemaadopcionbackend.dto;

import lombok.Data;

@Data
public class SimpleEmail {
    private String to;
    private String subject;
    private String body;

}