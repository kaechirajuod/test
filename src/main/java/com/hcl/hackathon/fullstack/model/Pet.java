package com.hcl.hackathon.fullstack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "APP_PET")
public class Pet implements Serializable {

	public Pet(String type, String breed, String url) {
		super();
		this.type = type;
		this.breed = breed;
		this.url = url;
	}

	public Pet() {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "BREED", nullable = false)
	private String breed;

	@Column(name = "IMAGE_URL", nullable = false)
	private String url;

	@Column(name = "VOTES")
	private Integer votes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Pet pet = (Pet) o;

		if (id != null ? !id.equals(pet.id) : pet.id != null)
			return false;
		if (type != null ? !type.equalsIgnoreCase(pet.type) : pet.type != null)
			return false;
		if (breed != null ? !breed.equalsIgnoreCase(pet.breed) : pet.breed != null)
			return false;
		return url != null ? url.equalsIgnoreCase(pet.url) : pet.url == null;
	}

	@Override
	public int hashCode() {
		int result;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (breed != null ? breed.hashCode() : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", type=" + type + ", breed=" + breed + ", url=" + url + ", votes=" + votes + "]";
	}

}
