package fr.hatclic.chatop.model;

import java.time.ZonedDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "messages")
public class Messages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "message", length = 2000)
	@Size(max = 2000)
	private String message;

	@NotNull
	@Column(name = "rental_id", nullable = false)
	private Long rental_id;

	@NotNull
	@Column(name = "user_id", nullable = false)
	private Long user_id;

	@Column(name = "created_at", updatable = false)
	private ZonedDateTime created_at;

	@Column(name = "updated_at")
	private ZonedDateTime updated_at;

	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

	public final String getMessage() {
		return message;
	}

	public final void setMessage(final String message) {
		this.message = message;
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

	public final Long getRental_id() {
		return rental_id;
	}

	public final void setRental_id(final Long rental_id) {
		this.rental_id = rental_id;
	}

	public final Long getUser_id() {
		return user_id;
	}

	public final void setUser_id(final Long user_id) {
		this.user_id = user_id;
	}

	@Override
	public final String toString() {
		return "messages{" + "id=" + id + '\'' + ", message='" + message + '\'' + ", rental_id='" + rental_id + '\''
				+ ", user_id='" + user_id + '\'' + ", created_at='" + created_at + '\'' + ", updated_at=" + updated_at
				+ '}';
	}

}
