package annuaire.services.imp;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import annuaire.services.IEmailSender;

/**
 * Classe de service d'envoi de mail. 
 * Basé sur l'API JavaMail.
 * 
 * @author Quentin Cheynet & Yoann Moisset
 *
 */

@Stateless
@LocalBean()
@Startup
public class EmailSender implements IEmailSender {

	/**
	 * Méthode permettant l'envoi d'un mail.
	 * 
	 * @param dest Adresse mail du destinataire.
	 * @param message Corps du message.
	 */
	@Override
	public void send(String dest, String message) {
		
		final String mailSender = "annuaire.jee@gmail.com";
		final String passwdSender ="annuaire33";
		
        Properties props = new Properties();
        
        props.setProperty("mail.user", mailSender);
        props.setProperty("mail.password", passwdSender);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");     
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailSender, passwdSender);
			}
		});
		        
		try{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(mailSender, "Quentin & Yoann"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(dest));
			msg.setSubject("Récupération de mot de passe");
			msg.setText(message);
			Transport.send(msg);
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
