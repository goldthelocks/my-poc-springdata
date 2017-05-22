/**
 * 
 */
package com.poc.springdatajpa.repository.main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poc.springdatajpa.model.main.Pet;

/**
 * @author eotayde
 *
 */
public interface PetRepository extends PBaseRepository<Pet>, JpaSpecificationExecutor<Pet>, PetRepositoryCustom {

	@Query("select p from Pet p where p.id = :id")
	Pet findPetById(@Param("id") Long id);
	
	@Query("select p from Pet p where p.id = ?1 AND p.petType = ?2")
	Pet findPetByIdAndType(Long id, String petType);
	
	@Query("select p from Pet p "
			+ "left join p.petDetails pd "
			+ "where pd.ageRange = :ageRange")
	List<Pet> findPetByAgeRange(@Param("ageRange") String ageRange);
	
	@Query("select p from Pet p where p.petDetails.petName in :name")
	List<Pet> findPetByNames(@Param("name") List<String> names);
	
}