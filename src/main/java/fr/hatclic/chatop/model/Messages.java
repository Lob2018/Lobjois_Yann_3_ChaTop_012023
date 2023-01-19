package fr.hatclic.chatop.model;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "messages")
public class Messages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({ @JoinColumn(name = "user_id", referencedColumnName = "id"), })
//	private Users user;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({ @JoinColumn(name = "rental_id", referencedColumnName = "id"), })
//	private Rentals rental;

	@Column(name = "message", length = 2000)
	@Size(max = 2000)
	private String message;

	@Column(name = "created_at")
	private ZonedDateTime created_at;

	@Column(name = "updated_at")
	private ZonedDateTime updated_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ZonedDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(ZonedDateTime created_at) {
		this.created_at = created_at;
	}

	public ZonedDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(ZonedDateTime updated_at) {
		this.updated_at = updated_at;
	}

//	public Users getUser() {
//		return user;
//	}
//
//	public void setUser(Users user) {
//		this.user = user;
//	}
//
//	public Rentals getRental() {
//		return rental;
//	}
//
//	public void setRental(Rentals rental) {
//		this.rental = rental;
//	}
	
	@Override
	public String toString() {
		return "messages{" + "id=" + id + ", message='" + message + '\'' + ", created_at='" + created_at + '\''
				+ ", updated_at=" + updated_at + '}';
	}

}
