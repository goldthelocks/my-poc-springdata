/**
 * 
 */
package com.poc.springdatajpa.model.main;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author eotayde
 *
 */
@Entity
@Table(name = "tblpet")
public class Pet {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "pet_type")
	private String petType;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pet_detail_id")
	private PetDetails petDetails;

	@Column(name = "date_added")
	private Date dateAdded;

	public Pet() {
	}

	public Pet(Long id, String petType, PetDetails petDetails, Date dateAdded) {
		super();
		this.id = id;
		this.petType = petType;
		this.petDetails = petDetails;
		this.dateAdded = dateAdded;
	}

	public Pet(String petType, Date dateAdded, PetDetails petDetails) {
		super();
		this.petType = petType;
		this.petDetails = petDetails;
		this.dateAdded = dateAdded;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public PetDetails getPetDetails() {
		return petDetails;
	}

	public void setPetDetails(PetDetails petDetails) {
		this.petDetails = petDetails;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", petType=" + petType + ", petDetails=" + petDetails + ", dateAdded=" + dateAdded
				+ "]";
	}

}
