/**
 * 
 */
package com.poc.springdatajpa.repository.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * @author eotayde
 *
 */
@NoRepositoryBean
public interface UBaseRepository<T> extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {
	
}
