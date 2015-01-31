package annuaire.web;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import annuaire.model.Group;

/**
 * Classe permettant de stocker en session les renseignements de l'utilisateur
 * utiles à sa navigation (vérification des accès).
 * 
 * @author Quentin Cheynet & Yoann Moisset
 *
 */

@Component()
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Statut de l'utilisateur en session.
     */
    private boolean connected = false;
    
    /**
     * Login de l'utilisateur en session.
     */
    private String login;
    
    /**
     * Mot de passe de l'utilisateur en session.
     */
    private String password;
    
    /**
     * Ensemble des groupes auxquels appartient l'utilisateur en session.
     */
    private Set<Group> groups;

    /**
     * Retourne l'ensemble des groupes auxquels appartient l'utilisateur
     * en session.
     * 
     * @return Ensemble des groupes.
     */
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * Fixe l'ensemble des groupes auxquels appartient l'utilisateur
     * en session.
	 * 
	 * @param groups Ensemble des groupes.
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	/**
	 * Retourne le login de l'utilisateur en session.
	 * 
	 * @return Login
	 */
	public String getLogin() {
        return login;
    }

	/**
	 * Fixe le login de l'utilisateur en session.
	 * 
	 * @param login Login de l'utilisateur.
	 */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Permet de vérifier le statut de l'utilisateur en session.
     * 
     * @return True si l'utilisateur est connecté, false sinon.
     */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Fixe le statut de l'utilisateur en session.
	 * 
	 * @param connected Valeur du statut.
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * Retourne le mot de passe de l'utilisateur en session.
	 * 
	 * @return Mot de passe
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Fixe le mot de passe de l'utilisateur en session.
	 * 
	 * @param password Mot de passe de l'utilisateur.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Permet de vérifier si l'utilisateur est un administrateur.
	 * 
	 * @return True si l'utilisateur est un administrateur, false sinon.
	 */
	public boolean isAdmin(){
		if(groups != null)
		for(Group g : groups){
			if (g.getGroupname().equals("ADMINISTRATEUR")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Permet de réinitialiser la session courante.
	 */
	public void flush() {
		connected = false;
		login = "";
		password = "";
		groups = new HashSet<Group>();
		groups.clear();
	}

}
