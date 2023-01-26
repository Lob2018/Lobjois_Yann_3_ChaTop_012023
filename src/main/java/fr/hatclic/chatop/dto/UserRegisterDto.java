package fr.hatclic.chatop.dto;

public class UserRegisterDto {
	private String name;
	private String email;
	private String password;

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(final String email) {
		this.email = email;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public final String toString() {
		return "usersNormalDto{" + '\'' + ", email='" + email + '\'' + ", name='" + name + '\'' + ", password='"
				+ password + '}';
	}
}
