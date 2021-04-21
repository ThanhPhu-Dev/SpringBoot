package cf.dinhthanhphu.utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtils {

	@Autowired
	public JavaMailSender emailSender;

	public void sendEmail(String recipientEmail, String link)
			throws MessagingException, UnsupportedEncodingException {
		
		
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		
//	    String content = "<p>Hello,</p>"
//	            + "<p>You have requested to reset your password.</p>"
//	            + "<p>Click the link below to change your password:</p>"
//	            + "<p><a href=\"" + link + "\">Change my password</a></p>"
//	            + "<br>"
//	            + "<p>Ignore this email if you do remember your password, "
//	            + "or you have not made the request.</p>";
//	    
//	    
//		simpleMailMessage.setFrom("sv18600207@gmail.com");
//		simpleMailMessage.setTo(recipientEmail);
//		simpleMailMessage.setSubject("Here's the link to reset your password");
//		simpleMailMessage.(content.toString());
//		emailSender.send(simpleMailMessage);
		
	    MimeMessage message = emailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("contact@shopme.com", "Shopme Support");
	    helper.setTo(recipientEmail);
	     
	    String subject = "Here's the link to reset your password";	     

	    String content = "<p>Hello,</p>"
        + "<p>You have requested to reset your password.</p>"
        + "<p>Click the link below to change your password:</p>"
        + "<p><a href=\"" + link + "\">Change my password</a></p>"
        + "<br>"
        + "<p>Ignore this email if you do remember your password, "
        + "or you have not made the request.</p>";
	    helper.setSubject(subject);
     
	    helper.setText(content, true);
	     
	    emailSender.send(message);
	}
}
