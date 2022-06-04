package br.org.catolicasc.hangman_java.dto;

import javax.validation.constraints.NotBlank;

import br.org.catolicasc.hangman_java.bean.User;

public class RequestUserCreate {

	@NotBlank
	private String name;

	@NotBlank
	private String login;

	@NotBlank
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User toUser() {
		User user = new User();
		user.setName(this.name);
		user.setLogin(this.login);
		user.setPassword(this.password);
		return user;
	}

}
