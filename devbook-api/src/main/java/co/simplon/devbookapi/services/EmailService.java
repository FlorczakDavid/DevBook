package co.simplon.devbookapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {

	@Value("${co.simplon.devbook.email.from}")
    private String emailFrom;
	
	@Value("${co.simplon.devbook.urlPinInput}")
	private String urlPinInput;

    private final JavaMailSender sender;
    
    public EmailService(JavaMailSender sender) {
    	this.sender=sender;
    }

    public void sendMail(String mail, String pin, String token ) {
	try {
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    helper.setFrom(emailFrom);
	    helper.setReplyTo(emailFrom);
	    helper.setTo(mail);
	    helper.setSubject("PIN authentication");
	    String htmlContent = """
	            <html>
	            <body>
	                <h2>Votre PIN pour vous connecter</h2>
	                <p>%s</p>
	                <p>Lien pour vous connecter : </p>
	                <p>%s/%s</p>
	            </body>
	            </html>
	            """.formatted(pin, this.urlPinInput, token);

	    helper.setText(htmlContent, true); 
	    sender.send(message);

	} catch (MessagingException e) {
	    System.err.println(e);
	}
    }

}
