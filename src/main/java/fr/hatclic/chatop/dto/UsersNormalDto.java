package fr.hatclic.chatop.dto;

import java.time.ZonedDateTime;

public class UsersNormalDto {
	private Long id;
	private String name;
	private String email;
	private String password;
	private ZonedDateTime created_at;
	private ZonedDateTime updated_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "usersNormalDto{"
	+ "id=" + id
	+ '\'' + ", email='" + email 
	+ '\'' + ", name='" + name 
	+ '\'' + ", password='"+ password 
	+ '\'' + ", created_at='" + created_at 
	+ '\'' + ", updated_at=" + updated_at 
	+ '}';
	}
}
