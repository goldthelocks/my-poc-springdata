/**
 * 
 */
package com.poc.springdatajpa.service.main;

import java.util.List;

import com.poc.springdatajpa.model.main.Pet;
import com.poc.springdatajpa.model.main.PetDto;


/**
 * @author eotayde
 *
 */
public interface PetService {

	void save(Pet pet);
	
	void delete(Pet pet);
	
	Pet findPet(Long id);
	
	Pet findPetByType(Long id, String petType);
	
	List<Pet> findAll();
	
	List<PetDto> findPetDto();
}
