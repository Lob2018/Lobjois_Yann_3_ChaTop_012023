package fr.hatclic.chatop.dto;

import java.time.ZonedDateTime;

public class RentalsDto {

	private Long id;
	private String name;
	private double surface;
	private double price;
	private String picture;
	private String description;
	private Long owner_id;
	private ZonedDateTime created_at;
	private ZonedDateTime updated_at;

	public final void setRentalsDto(final Long id, final String name, final double surface, final double price,
			final String picture, final String description, final Long owner_id, final ZonedDateTime created_at,
			final ZonedDateTime updated_at) {
		this.id = id;
		this.name = name;
		this.surface = surface;
		this.price = price;
		if (picture != null)
			this.picture = picture;
		this.description = description;
		this.owner_id = owner_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

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

	public final void setPrice(final double price) {
		this.price = price;
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

	public final Long getOwner_id() {
		return owner_id;
	}

	public final void setOwner_id(final Long owner_id) {
		this.owner_id = owner_id;
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
		return "rentals{" + "id=" + id + '\'' + ", name='" + name + '\'' + ", surface='" + surface + '\'' + ", price='"
				+ price + '\'' + ", picture='" + picture + '\'' + ", description='" + description + '\''
				+ ", owner_id='" + owner_id + '\'' + ", created_at='" + created_at + '\'' + ", updated_at=" + updated_at
				+ '}';
	}
}
