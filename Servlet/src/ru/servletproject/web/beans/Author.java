package ru.servletproject.web.beans;

public class Author {

	public Author() {

	}

	private String name;

	public Author(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
