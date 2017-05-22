/**
 * 
 */
package com.poc.springdatajpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poc.springdatajpa.model.main.Pet;
import com.poc.springdatajpa.model.main.PetDetails;
import com.poc.springdatajpa.model.main.PetDto;
import com.poc.springdatajpa.service.main.PetService;

/**
 * @author eotayde
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class PetServiceTest {

	@Autowired
	private PetService petService;
	
	private final static Logger logger = LoggerFactory.getLogger(PetServiceTest.class);
		
	public void testDto() {
		save();
		
		List<PetDto> petDto = petService.findPetDto();
		
		for (PetDto d : petDto) {
			System.out.println(d.toString());
		}
		
	}
	
	public void testFindAll() {
		save();	
		
		logger.debug("fetching...");
		
		System.out.println("Fetching");
		System.out.println(System.currentTimeMillis() + ": " + petService.findAll());
		
		System.out.println(System.currentTimeMillis() + ": " + petService.findAll());
		System.out.println(System.currentTimeMillis() + ": " + petService.findAll());
		
	}
	
	
	
	@Test
	public void testFindByPetType() {
		save();	
		
		logger.debug("fetching...");
		
		System.out.println("Fetching");
		System.out.println(System.currentTimeMillis() + ": " + petService.findPetByType(1L, "cat").toString());
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPetByType(1L, "cat").toString());
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPetByType(1L, "cat").toString());
		
		System.out.println("two");
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPetByType(2L, "cat").toString());
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPetByType(2L, "cat").toString());
		
		System.out.println("updated mama cat");
		petService.save(new Pet(2L, "cat", new PetDetails("Mama Cat 2", "Old", 2L, "Domestic Cat", "Female"), new Date()));
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPetByType(2L, "cat").toString());
		
		petService.delete(new Pet(2L, "cat", new PetDetails("Mama Cat 2", "Old", 2L, "Domestic Cat", "Female"), new Date()));
		System.out.println("deleted mama cat");
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPetByType(2L, "cat").toString());
		
	}
	
	public void testFindCache() {
		save();	
		
		logger.debug("fetching...");
		
		System.out.println("Fetching");
		System.out.println(System.currentTimeMillis() + ": " + petService.findPet(1L).toString());
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPet(1L).toString());
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPet(1L).toString());
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPet(2L).toString());
		System.out.println(System.currentTimeMillis() + ": "+ petService.findPet(2L).toString());
	}
	
	public void save() {
		List<Pet> listOfPets = new ArrayList<Pet>();
		
		// cats
		Pet cat1 = new Pet("cat", new Date(), new PetDetails("Akai", "Young", 1L, "American Shorthair", "Male"));
		Pet cat2 = new Pet("cat", new Date(), new PetDetails("Mama Cat", "Old", 2L, "Domestic Cat", "Female"));
		Pet cat3 = new Pet("cat", new Date(), new PetDetails("Shabby Meow", "Old", 3L, "Domestic Cat", "Male"));
		
		listOfPets.add(cat1);
		listOfPets.add(cat2);
		listOfPets.add(cat3);
		
		// dogs
		Pet dog1 = new Pet("dog", new Date(), new PetDetails("Basha", "Young", 1L, "Shih Tzu", "Female"));
		Pet dog2 = new Pet("dog", new Date(), new PetDetails("Popoy", "Old", 2L, "Shih Tzu", "Male"));
		Pet dog3 = new Pet("dog", new Date(), new PetDetails("Russia", "Old", 1L, "Husky", "Male"));
		
		listOfPets.add(dog1);
		listOfPets.add(dog2);
		listOfPets.add(dog3);
		
		for (Pet p : listOfPets) {
			petService.save(p);
		}
	}
}
