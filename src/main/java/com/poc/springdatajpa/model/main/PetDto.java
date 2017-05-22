/**
 * 
 */
package com.poc.springdatajpa.model.main;

/**
 * @author eotayde
 *
 */
public class PetDto {

	private String petType;
	private String petName2;

	public PetDto() {
		super();
	}

	public PetDto(String petType, String petName2) {
		super();
		this.petType = petType;
		this.petName2 = petName2;
	}

	@Override
	public String toString() {
		return "PetDto [petType=" + petType + ", petName2=" + petName2 + "]";
	}

}
