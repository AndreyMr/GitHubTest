package main.webapp.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import main.webapp.enums.SearchType;

public class SearchController implements Serializable {
	private SearchType searchType;
	private static Map<String, SearchType> searchList = new HashMap<String, SearchType>();

	public SearchController() {
		ResourceBundle bundle = ResourceBundle.getBundle("main.webapp.resources.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		searchList.put(bundle.getString("select_author"), SearchType.AUTHOR);
		searchList.put(bundle.getString("select_bookName"), SearchType.TITLE);
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public Map<String, SearchType> getSearchList() {
		return searchList;
	}
}
