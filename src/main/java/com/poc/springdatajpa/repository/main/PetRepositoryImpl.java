/**
 * 
 */
package com.poc.springdatajpa.repository.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;

import com.poc.springdatajpa.model.main.Pet;
import com.poc.springdatajpa.model.main.PetDto;
import com.poc.springdatajpa.specification.JpaQueryBuilder;

/**
 * @author eotayde
 * @param <T>
 *
 */
public class PetRepositoryImpl implements PetRepositoryCustom {

	@Autowired
	@PersistenceContext(unitName = "poc_entityManagerFactory")
	protected EntityManager entityManager;
	
	public List<Pet> findCustomCondition() {		
		JpaQueryBuilder myJpa = new JpaQueryBuilder(Pet.class, entityManager);
		myJpa.hasIdClass(false);
		myJpa.equal("petType", "cat");
		myJpa.equal("petDetails.ageRange", "Young");		
		myJpa.equal("petDetails.breed", "Domestic Cat");		
		
		myJpa.addOrder("petDetails.petName", Direction.ASC);
		myJpa.addOrder("id", Direction.DESC);		
		
		return (List<Pet>) myJpa.getList();
	}

	public Pet findSingle() {		
		JpaQueryBuilder myJpa = new JpaQueryBuilder(Pet.class, entityManager);
		myJpa.equal("id", 1);
		myJpa.equal("petDetails.ageRange", "Young");
		
		return (Pet) myJpa.getOne();
	}

	public void forceDelete(Pet pet) {
		Query query = entityManager.createNativeQuery("delete from tblpet p where p.id = ?1 and pet_type = ?2");
		query.setParameter(1, 1);
		query.setParameter(2, "cat");
		query.executeUpdate();
	}

	public List<PetDto> findPetDto() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PetDto> criteriaQuery = cb.createQuery(PetDto.class);
		Root<Pet> from = criteriaQuery.from(Pet.class);
		criteriaQuery.select(cb.construct(PetDto.class,
				from.get("petType"),
				from.join("petDetails").get("petName")));

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
