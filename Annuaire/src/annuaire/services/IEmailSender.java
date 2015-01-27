package annuaire.services;

public interface IEmailSender {
	public void send(String dest, String message);
}
