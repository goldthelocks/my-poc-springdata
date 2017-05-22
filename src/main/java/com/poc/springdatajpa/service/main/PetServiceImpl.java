/**
 * 
 */
package com.poc.springdatajpa.service.main;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc.springdatajpa.model.main.Pet;
import com.poc.springdatajpa.model.main.PetDetails;
import com.poc.springdatajpa.model.main.PetDto;
import com.poc.springdatajpa.repository.main.PetRepository;

/**
 * @author eotayde
 *
 */
@Service
@Transactional("poc_transactionManager")
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository repository;

	@CacheEvict(value = "petCache", key = "#pet.id")
	public void save(Pet pet) {
		repository.save(pet);		
	}

	@CacheEvict(value = "petCache", key = "#pet.id")
	public void delete(Pet pet) {
		repository.delete(pet);		
	}

	@Cacheable( value = "petCache", key = "#id")
	public Pet findPet(Long id) {
		simulateSlowService();
		return repository.findOne(id);
	}

	@Cacheable( value = "petCache", key = "#id")
	public Pet findPetByType(Long id, String petType) {
		simulateSlowService();
		return repository.findPetByIdAndType(id, petType);
	}

	@Cacheable(value = "petCache")
	public List<Pet> findAll() {
		simulateSlowService();
		return repository.findAll();
	}

	public List<PetDto> findPetDto() {
		return repository.findPetDto();
	}
	
    private void simulateSlowService() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
	
	
}
