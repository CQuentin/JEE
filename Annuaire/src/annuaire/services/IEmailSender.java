package annuaire.services;

/**
* IEmailSender Interface pour la classe EmailSender, 
* la classe qui permet d'envoyer des mails.
*
* @author Quentin Cheynet
* @author Yoann Moisset
*/

public interface IEmailSender {
	
	/**
	 * Méthode permettant l'envoi d'un mail.
	 * 
	 * @param dest Adresse mail du destinataire.
	 * @param message Corps du message.
	 */
	public void send(String dest, String message);
}
