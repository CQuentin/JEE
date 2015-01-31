package annuaire.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Classe permettant de vérifier si l'utilisateur est authentifié
 * avant l'appel des méthodes correspondantes aux URL décritent 
 * dans le fichier de configuration "springapp-servlet.xml".
 * 
 * @author Quentin Cheynet & Yoann Moisset
 *
 */
public class Interceptor extends HandlerInterceptorAdapter {

	/**
	 * L'utilisateur en session.
	 * 
	 * @see User
	 */
	@Autowired
	User user;

	/**
	 * Appelée avant chaque appel des méthodes correspondantes aux URL décritent 
	 * dans le fichier de configuration "springapp-servlet.xml".
	 * Si l'utilisateur n'est pas authentifié, il est redirigé vers le
	 * le formulaire d'authentification.
	 * 
	 * @return True si l'utilisateur est authentifié, false sinon.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		if (!request.getRequestURI().equals("/Annuaire/auth/login.htm")) {
			if (!user.isConnected()) {
				response.sendRedirect("/Annuaire/auth/login.htm");
				return false;
			}
		}
		return true;
	}
}
