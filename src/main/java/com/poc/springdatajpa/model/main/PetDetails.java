/**
 * 
 */
package com.poc.springdatajpa.model.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author eotayde
 *
 */
@Entity
@Table(name = "tblpet_details")
public class PetDetails {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "pet_name")
	private String petName;

	@Column(name = "age_range")
	private String ageRange;

	@Column
	private Long age;

	@Column
	private String breed;

	@Column
	private String gender;

	public PetDetails() {
	}

	public PetDetails(Long id, String petName, String ageRange, Long age, String breed, String gender) {
		super();
		this.id = id;
		this.petName = petName;
		this.ageRange = ageRange;
		this.age = age;
		this.breed = breed;
		this.gender = gender;
	}

	public PetDetails(String petName, String ageRange, Long age, String breed, String gender) {
		super();
		this.petName = petName;
		this.ageRange = ageRange;
		this.age = age;
		this.breed = breed;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "PetDetails [id=" + id + ", petName=" + petName + ", ageRange=" + ageRange + ", age=" + age + ", breed="
				+ breed + ", gender=" + gender + "]";
	}

}
