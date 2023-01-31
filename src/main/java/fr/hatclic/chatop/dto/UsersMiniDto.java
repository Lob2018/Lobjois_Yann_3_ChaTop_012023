package fr.hatclic.chatop.dto;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsersMiniDto {

	private Long id;
	
	@NotBlank
	@Size(max = 255)
	private String name;

	@NotBlank
	@Size(max = 255)
	private String email;

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

	@Override
	public final String toString() {
		return "usersMiniDto{" + "id=" + id + '\'' + ", email='" + email + '\'' + ", name='" + name + '\''
				+ ", created_at='" + created_at + '\'' + ", updated_at=" + updated_at + '}';
	}

}
