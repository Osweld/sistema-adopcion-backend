package ues.dsi.sistemaadopcionbackend.exceptions;

public class DuplicateEntityException extends RuntimeException{
    private String mensaje;

    public DuplicateEntityException(String mensaje){
        super(mensaje);
        this.mensaje =mensaje;
    }

    public String getField(){
        return mensaje;
    }
}
