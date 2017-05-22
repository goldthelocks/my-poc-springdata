/**
 * 
 */
package com.poc.springdatajpa.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc.springdatajpa.model.user.User;
import com.poc.springdatajpa.repository.user.UBaseRepository;
import com.poc.springdatajpa.repository.user.UserRepository;


/**
 * @author eotayde
 *
 */
@Service
@Transactional("user_transactionManager")
public class UserServiceImpl implements UserService {

	@Autowired
	private UBaseRepository<User> repository;
		
	public void save(User User) {
		repository.save(User);
	}

	/* (non-Javadoc)
	 * @see com.poc.springdatajpa.service.user.UserService#findOne(java.lang.Long)
	 */
	public User findOne(Long id) {
		return repository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.poc.springdatajpa.service.user.UserService#findAll()
	 */
	public List<User> findAll() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.poc.springdatajpa.service.user.UserService#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		repository.delete(id);	
	}

	/* (non-Javadoc)
	 * @see com.poc.springdatajpa.service.user.UserService#findAllWithCustom()
	 */
	public List<User> findAllWithCustom() {
//		return repository.findAllByPassword();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.poc.springdatajpa.service.user.UserService#findWithOrder()
	 */
	public List<User> findWithOrder() {
//		return repository.findAllByOrderByUsernameAsc();
		return null;
	}


}
