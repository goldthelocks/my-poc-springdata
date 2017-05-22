/**
 * 
 */
package com.poc.springdatajpa.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.poc.springdatajpa.model.user.User;

/**
 * @author eotayde
 *
 */
public interface UserRepository extends UBaseRepository<User> {

	@Query("select u from User u where password <> 'hello'")
	List<User> findAllByPassword();
	
	List<User> findAllByOrderByUsernameAsc();
	
}
