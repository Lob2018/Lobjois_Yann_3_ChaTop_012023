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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", length = 255)
	@Size(max = 255)
	private String email;

	@Column(name = "name", length = 255)
	@Size(max = 255)
	private String name;

	@Column(name = "password", length = 255)
	@Size(max = 255)
	private String password;

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
	
	@Override
	public String toString() {
		return "messages{" +
				"id=" + id +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", created_at='" + created_at + '\'' +
				", updated_at=" + updated_at +
				'}';
	}

}
