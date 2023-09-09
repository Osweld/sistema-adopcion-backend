package ues.dsi.sistemaadopcionbackend.services;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;
import ues.dsi.sistemaadopcionbackend.dto.SimpleEmail;
import ues.dsi.sistemaadopcionbackend.dto.User;

import java.io.IOException;

public interface EmailService {

    public void sendSimpleEmail(String to,String subject,String body);
    public void sendEmailWithAttachments(SimpleEmail simpleEmail, MultipartFile[]  multipartFile) throws MessagingException;
    public void sendMultipleDestinationEmail(String[] to,String subject,String body);
    public void sendEmailWithTemplate(String to,String subject,String body) throws MessagingException;
    public void sendEmailWithFreeMakerTemplate(User user) throws MessagingException, IOException, TemplateException;
}
