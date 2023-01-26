package fr.hatclic.chatop.dto;

import java.time.ZonedDateTime;

public class UsersNormalDto {
	private Long id;
	private String name;
	private String email;
	private String password;
	private ZonedDateTime created_at;
	private ZonedDateTime updated_at;

	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

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

	public final ZonedDateTime getCreated_at() {
		return created_at;
	}

	public final void setCreated_at(final ZonedDateTime created_at) {
		this.created_at = created_at;
	}

	public final ZonedDateTime getUpdated_at() {
		return updated_at;
	}

	public final void setUpdated_at(final ZonedDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public final String toString() {
		return "usersNormalDto{" + "id=" + id + '\'' + ", email='" + email + '\'' + ", name='" + name + '\''
				+ ", password='" + password + '\'' + ", created_at='" + created_at + '\'' + ", updated_at=" + updated_at
				+ '}';
	}
}
