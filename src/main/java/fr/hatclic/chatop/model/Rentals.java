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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rentals")
public class Rentals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 255)
	@Size(max = 255)
	private String name;

	@Column(name = "surface")
	private double surface;

	@Column(name = "price")
	private double price;

	@Column(name = "picture", length = 255)
	@Size(max = 255)
	private String picture;

	@Column(name = "description", length = 2000)
	@Size(max = 2000)
	private String description;

	@Column(name = "owner_id", nullable = false)
	private Long owner_id;

	@Column(name = "created_at", updatable = false)
	private ZonedDateTime created_at;

	@Column(name = "updated_at")
	private ZonedDateTime updated_at;

	/**
	 * Messages for this rental
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "rental_id")
	List<Messages> messages = new ArrayList<>();

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double prix) {
		this.price = prix;
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

	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}

	public Long getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Long owner_id) {
		this.owner_id = owner_id;
	}

	@Override
	public String toString() {
		return "rentals{" + "id=" + id + ", name='" + name + '\'' + ", surface='" + surface + '\'' + ", price='" + price
				+ '\'' + ", picture='" + picture + '\'' + ", description='" + description + '\'' + ", created_at='"
				+ created_at + '\'' + ", updated_at='" + updated_at + '\'' + '}';
	}

}
