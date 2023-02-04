package fr.hatclic.chatop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MessagesDto {

	@NotBlank
	@Size(max = 2000)
	private String message;

	@NotNull
	private Long rental_id;

	@NotNull
	private Long user_id;

	public final String getMessage() {
		return message;
	}

	public final void setMessage(String message) {
		this.message = message;
	}

	public final Long getRental_id() {
		return rental_id;
	}

	public final void setRental_id(Long rental_id) {
		this.rental_id = rental_id;
	}

	public final Long getUser_id() {
		return user_id;
	}

	public final void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Override
	public final String toString() {
		return "messagesDto{" + '\'' + ", message='" + message + '\'' + ", rental_id='" + rental_id + '\''
				+ ", user_id='" + user_id + '}';
	}
}
