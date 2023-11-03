package ues.dsi.sistemaadopcionbackend.models.DTO;

import lombok.Data;

@Data
public class MascotaIdDTO {
    private Long id;

    public MascotaIdDTO(Long id) {
        this.id = id;
    }

}
