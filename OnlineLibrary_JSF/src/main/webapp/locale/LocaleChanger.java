package main.webapp.locale;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

public class LocaleChanger implements Serializable {
	private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	public LocaleChanger() {
	}
	/*
	 * public void changeLocaleEN() { currentLocale = new Locale("en"); }
	 * 
	 * public void changeLocaleRU() { currentLocale = new Locale("ru"); }
	 */

	public void cangeLocale(String localeCode) {
		// TODO Auto-generated method stub
		currentLocale = new Locale(localeCode);
		// FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

}
