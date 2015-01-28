package annuaire.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	User user;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		if (!request.getRequestURI().equals("/Annuaire/person/annuaire.htm")) {
			if (user.isConnected()) {
				response.sendRedirect("/Annuaire/person/annuaire.htm");
				return false;
			}
		}
		return true;
	}

}
