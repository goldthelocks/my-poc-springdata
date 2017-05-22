/**
 * 
 */
package com.poc.springdatajpa.repository.main;

import java.util.List;

import com.poc.springdatajpa.model.main.Pet;
import com.poc.springdatajpa.model.main.PetDto;

/**
 * @author eotayde
 *
 */
public interface PetRepositoryCustom {

	List<Pet> findCustomCondition();
	
	Pet findSingle();
	
	void forceDelete(Pet pet);
	
	List<PetDto> findPetDto();
	
}
