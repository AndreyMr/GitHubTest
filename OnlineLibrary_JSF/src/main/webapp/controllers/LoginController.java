package main.webapp.controllers;

import javax.faces.context.FacesContext;

public class LoginController {

	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String login() {
		return "books";
	}

	public String exit() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "exit";
	}
}
