/**
 * 
 */
package com.poc.springdatajpa.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author eotayde
 *
 */
@NoRepositoryBean
public interface PBaseRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
