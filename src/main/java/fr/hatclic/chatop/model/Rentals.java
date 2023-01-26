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

	public final double getSurface() {
		return surface;
	}

	public final void setSurface(final double surface) {
		this.surface = surface;
	}

	public final double getPrice() {
		return price;
	}

	public final void setPrice(final double prix) {
		this.price = prix;
	}

	public final String getPicture() {
		return picture;
	}

	public final void setPicture(final String picture) {
		this.picture = picture;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(final String description) {
		this.description = description;
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

	public final List<Messages> getMessages() {
		return messages;
	}

	public final void setMessages(final List<Messages> messages) {
		this.messages = messages;
	}

	public final Long getOwner_id() {
		return owner_id;
	}

	public final void setOwner_id(final Long owner_id) {
		this.owner_id = owner_id;
	}

	@Override
	public final String toString() {
		return "rentals{" + "id=" + id + '\'' + ", name='" + name + '\'' + ", surface='" + surface + '\'' + ", price='"
				+ price + '\'' + ", picture='" + picture + '\'' + ", description='" + description + '\''
				+ ", created_at='" + created_at + '\'' + ", updated_at='" + updated_at + '\'' + '}';
	}

}
