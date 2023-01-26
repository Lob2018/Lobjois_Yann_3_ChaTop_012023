package fr.hatclic.chatop.dto;

public class UsersLoginDto {

	private String login;
	private String password;
	private String email;

	public final String getLogin() {
		return login;
	}

	public final void setLogin(final String login) {
		this.login = login;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(final String password) {
		this.password = password;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public final String toString() {
		return "usersMiniDto{" + "login=" + login + "email=" + email + '\'' + ", password='" + password + '}';
	}

}
