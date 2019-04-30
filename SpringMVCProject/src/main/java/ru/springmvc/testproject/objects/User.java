package ru.springmvc.testproject.objects;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	@Size(min = 6, message = "{name.size.error}")
	private String name;

	@Size(min = 5, max = 10, message = "{password.size.error}")
	private String password;

	private boolean admin;
	private long time;

	public User() {
		super();
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
