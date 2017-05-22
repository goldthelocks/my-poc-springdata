/**
 * 
 */
package com.poc.springdatajpa.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;

/**
 * @author eotayde
 *
 */
public class AbstractCustomRepository<T> {

	@Autowired
	@PersistenceContext(unitName = "poc_entityManagerFactory")
	protected EntityManager entityManager;

	public CriteriaQuery<T> getSelectQuery(CriteriaBuilder cb) {
		CriteriaQuery<T> criteriaQuery = cb.createQuery(getDomainClass());
		Root<T> from = criteriaQuery.from(getDomainClass());
		return criteriaQuery;
	}
	
	public List<T> findWithCondition(Specification<T> specs) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = cb.createQuery(getDomainClass());
		Root<T> from = criteriaQuery.from(getDomainClass());
		criteriaQuery.select(from).where(specs.toPredicate(from, criteriaQuery, cb));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public List<T> findWithCondition(Specification<T> specs, Sort sorts) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = cb.createQuery(getDomainClass());
		Root<T> from = criteriaQuery.from(getDomainClass());
		criteriaQuery.select(from).where(specs.toPredicate(from, criteriaQuery, cb));			
		criteriaQuery.orderBy(QueryUtils.toOrders(sorts, from, cb));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public T findByCondition(Specification<T> specs) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = cb.createQuery(getDomainClass());
		Root<T> from = criteriaQuery.from(getDomainClass());
		criteriaQuery.select(from).where(specs.toPredicate(from, criteriaQuery, cb));
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}
	
	public CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}
	
	private Class getDomainClass() {
	     ParameterizedType parameterizedType = (ParameterizedType)getClass()
	                                                 .getGenericSuperclass();
	     return (Class) parameterizedType.getActualTypeArguments()[0];
	}
	
}
