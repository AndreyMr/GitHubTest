package main.webapp.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("main.webapp.validators.LoginValidator")
public class LoginValidator implements Validator<String> {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, String arg2) throws ValidatorException {
		ResourceBundle bundle = ResourceBundle.getBundle("main.webapp.resources.massages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

		if (Character.toString(arg2.charAt(0)).matches("[-+]?[\\d\\s]+")) {
			FacesMessage message = new FacesMessage(bundle.getString("login_begin_number_error"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (arg2.length() < 5) {
			FacesMessage message = new FacesMessage(bundle.getString("login_lenght_error"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (arg2.equals("username")) {
			FacesMessage message = new FacesMessage(bundle.getString("login_check_username_error"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		if (arg2.equals("login")) {
			FacesMessage message = new FacesMessage(bundle.getString("login_check_login_error"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

	}

}
