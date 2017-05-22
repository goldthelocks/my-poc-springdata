/**
 * 
 */
package com.poc.springdatajpa.service.user;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.poc.springdatajpa.model.user.User;


/**
 * @author eotayde
 *
 */
public interface UserService {

	void save(User User);

	void delete(Long id);
	
	User findOne(Long id);
	
	List<User> findAll();
	
	List<User> findAllWithCustom();
	
	List<User> findWithOrder();
	
}
