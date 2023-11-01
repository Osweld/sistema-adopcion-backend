package ues.dsi.sistemaadopcionbackend.services;

import ues.dsi.sistemaadopcionbackend.models.DTO.SimpleEmail;

public interface EmailService {
    public void sendPasswordResetEmail(String to, String passwordTemporal);
    public String generateTemporaryPassword();
}