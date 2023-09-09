package ues.dsi.sistemaadopcionbackend.services;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ues.dsi.sistemaadopcionbackend.dto.SimpleEmail;
import ues.dsi.sistemaadopcionbackend.dto.User;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String email;

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    public EmailServiceImpl(JavaMailSender javaMailSender, Configuration configuration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    @Override
    public void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);

    }

    @Override
    public void sendEmailWithAttachments(SimpleEmail simpleEmail, MultipartFile[] multipartFile) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom(email);
        helper.setTo(simpleEmail.getTo());
        helper.setSubject(simpleEmail.getSubject());
        helper.setText(simpleEmail.getBody());
        for(MultipartFile file: multipartFile){
            helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()),file);
        }

        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendMultipleDestinationEmail(String[] to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);

    }

    @Override
    public void sendEmailWithTemplate(String to, String subject, String body) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(email);
        mimeMessage.setRecipients(Message.RecipientType.TO,to);
        mimeMessage.setSubject(subject);
        String htmlContent = "<h1>Esta es una prueba</h1>" +
                "<p>"+body+"</p>";
        mimeMessage.setContent(htmlContent, "text/html; charset=utf-8");
        javaMailSender.send(mimeMessage);

    }

    @Override
    public void sendEmailWithFreeMakerTemplate(User user) throws MessagingException, IOException, TemplateException {
        // freemaker configuration
        StringWriter stringWriter = new StringWriter();
        Map<String,Object> body = new HashMap<>();
        body.put("user", user);
        configuration.getTemplate("email.ftl").process(body,stringWriter);
        // create mimemessage
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setSubject(user.getSubject());
        helper.setTo(user.getTo());
        String htmlContent = stringWriter.getBuffer().toString();
        helper.setText(htmlContent, true);

        javaMailSender.send(mimeMessage);
    }

}