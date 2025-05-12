package co.simplon.devbookapi.services;

import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.entities.EmailConfirmation;
import co.simplon.devbookapi.repositories.EmailConfirmationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class EmailConfirmationService {
    @Value("${co.simplon.devbook.email.from}")
    private String emailFrom;

    @Value("${co.simplon.devbook.urlEmailConfirmation}")
    private String urlEmailConfirmation;

    private final JavaMailSender mailSender;

    private final EmailConfirmationRepository emailConfirmationRepository;

    public EmailConfirmationService(JavaMailSender mailSender, EmailConfirmationRepository tokenRepository) {
        this.mailSender = mailSender;
        this.emailConfirmationRepository = tokenRepository;
    }


    public void sendConfirmationEmail(Account account) {
        String token = UUID.randomUUID().toString();
        EmailConfirmation emailConfirmation = new EmailConfirmation();
        emailConfirmation.setUuidToken(token);
        emailConfirmation.setAccount(account);
        emailConfirmation.setCreation(LocalDateTime.now());
        emailConfirmation.setExpiration(LocalDateTime.now().plusHours(24));
        emailConfirmationRepository.save(emailConfirmation);


        String subject = "Confirmez votre compte";
        String body = "<p>Veuillez cliquer sur le lien suivant pour confirmer la création de votre compte :</p>"
                + "<p><a href=\"" + urlEmailConfirmation + token + "\">Confirmer mon compte</a></p>";


        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailFrom);
            helper.setTo(account.getUsername());
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Erreur lors de l'envoi de l'email", e);
        }
    }



}