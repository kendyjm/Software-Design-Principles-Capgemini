package org.formation.controler;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.formation.model.Member;
import org.formation.service.AuthenticationException;
import org.formation.service.UserDocumentServiceLocal;

@Named("loginController")
@RequestScoped
public class LoginController {

	// Please inject the service as EJB 
	UserDocumentServiceLocal userDocumentService;
	
	private String email,password;
	
	public LoginController() {
	}

	public String login() {
		// Use service yto authenticate users
		// If authentication succed, go to documents
		// else remain on login
		return "";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
