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
@Table(name = "rentals")
public class Rentals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({ @JoinColumn(name = "owner_id", referencedColumnName = "id"), })
//	private Users user;

	@Column(name = "name", length = 255)
	@Size(max = 255)
	private String name;

	@Column(name = "surface")
	private double surface;

	@Column(name = "prix")
	private double prix;

	@Column(name = "picture", length = 255)
	@Size(max = 255)
	private String picture;

	@Column(name = "description", length = 2000)
	@Size(max = 2000)
	private String description;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	@Override
	public String toString() {
		return "rentals{" + "id=" + id + ", name='" + name + '\'' + ", surface='" + surface + '\'' + ", prix='" + prix
				+ '\'' + ", picture='" + picture + '\'' + ", description='" + description + '\'' + ", created_at='"
				+ created_at + '\'' + ", updated_at='" + updated_at + '\'' + '}';
	}

}
