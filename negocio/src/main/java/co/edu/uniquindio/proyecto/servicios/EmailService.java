package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public String sendEmail(SendEmail request) {
        MimeMessage msj = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msj,true);
            helper.setTo(request.getToEmail());
            helper.setFrom(request.getFrom());
            helper.setText(request.getBody());
            helper.setSubject(request.getSubject());
            mailSender.send(msj);
        } catch (MessagingException e) {
            return "Correo fallido"+e.getMessage();
        }
        return "Correo enviado";


    }
}

