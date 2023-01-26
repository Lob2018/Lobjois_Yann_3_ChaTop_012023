package fr.hatclic.chatop.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "email", length = 255)
	@Size(max = 255)
	private String email;

	@Column(name = "name", length = 255)
	@Size(max = 255)
	private String name;

	@NotBlank
	@Column(name = "password", length = 255)
	@Size(max = 255)
	private String password;

	@Column(name = "created_at", updatable = false)
	private ZonedDateTime created_at;

	@Column(name = "updated_at")
	private ZonedDateTime updated_at;

	/**
	 * Rentals owner
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id")
	List<Rentals> rentals = new ArrayList<>();

	/**
	 * Emitted messages
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	List<Messages> messages = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Rentals> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rentals> rentals) {
		this.rentals = rentals;
	}

	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "user{" 
	+ "id=" + id 
	+ ", email='" + email + '\'' 
	+ ", name='" + name + '\'' 
	+ ", password='" + password + '\'' 
	+ ", created_at='" + created_at + '\'' 
	+ ", updated_at=" + updated_at + '}';
	}

}
