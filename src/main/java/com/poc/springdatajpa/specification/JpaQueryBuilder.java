/**
 * 
 */
package com.poc.springdatajpa.specification;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Predicate.BooleanOperator;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.util.Assert;

/**
 * This helper class can be used to build dynamic queries.
 * 
 * @author Eraine Otayde
 *
 */
public class JpaQueryBuilder {

	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private CriteriaQuery criteriaQuery;
	private Root from;

	private Class clazz;
	private Path<?> path;

	private List<Predicate> predicateList;
	private List<Order> orderList;

	private Sort sort;

	private boolean hasIdClass;
	private String idPropertyName;

	private Predicate predicate;

	private BooleanOperator operator;

	public JpaQueryBuilder(Class clazz, EntityManager entityManager) {
		this.clazz = clazz;
		this.entityManager = entityManager;
		predicateList = new ArrayList<Predicate>();
		orderList = new ArrayList<Sort.Order>();
		criteriaBuilder = getEntityManager().getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(clazz);
		from = criteriaQuery.from(clazz);
	}

	/**
	 * This will return a list of entities queried. If there were query
	 * conditions set, it will return the filtered list.
	 * 
	 * @return list
	 */
	public List<?> getList() {
		// if there is no predicate in the list just do a simple query
		if (predicateList.size() == 0) {
			criteriaQuery.select(from);
		} else {
			criteriaQuery.select(from).where(predicateList.toArray(new Predicate[] {}));
		}

		// if there are orders
		if (orderList.size() > 0) {
			criteriaQuery.orderBy(QueryUtils.toOrders(sort, from, criteriaBuilder));
		}

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

	/**
	 * This will return an object. If there were query conditions set, it will
	 * return the filtered object.
	 * 
	 * @return object
	 */
	public Object getOne() {
		if (predicateList.size() == 0) {
			criteriaQuery.select(from);
		} else {
			criteriaQuery.select(from).where(predicateList.toArray(new Predicate[] {}));
		}

		return getEntityManager().createQuery(criteriaQuery).getSingleResult();
	}

	/**
	 * This will return the row count of your search criteria.
	 * 
	 * @return object
	 */
	public Long getRowCount() {
		// if there is no predicate in the list just do a simple query
		if (predicateList.size() == 0) {
			criteriaQuery.select(from);
		} else {
			criteriaQuery.select(from).where(predicateList.toArray(new Predicate[] {}));
		}

		// if there are orders
		if (orderList.size() > 0) {
			criteriaQuery.orderBy(QueryUtils.toOrders(sort, from, criteriaBuilder));
		}

		int size = getEntityManager().createQuery(criteriaQuery).getResultList().size();

		return Long.valueOf(size);
	}

	/**
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void equal(String propertyName, final Object value) {
		doEqual(propertyName, value);
	}

	/**
	 * @param propertyName
	 * @param value
	 */
	public void notEqual(String propertyName, final Object value) {
		doNotEqual(propertyName, value);
	}

	/**
	 * @param propertyName
	 */
	public void isNull(String propertyName) {
		doIsNull(propertyName);
	}

	/**
	 * @param propertyName
	 */
	public void isNotNull(String propertyName) {
		doIsNotNull(propertyName);
	}

	/**
	 * @param propertyName
	 * @param values
	 */
	public void in(String propertyName, Collection<?> values) {
		doIn(propertyName, values);
	}

	/**
	 * 
	 * @param propertyName
	 * @param values
	 */
	public void notIn(String propertyName, Collection<?> values) {
		doNotin(propertyName, values);
	}

	/**
	 * 
	 * @param propertyName
	 * @param pattern
	 */
	public void like(String propertyName, String pattern) {
		doLike(propertyName, pattern);
	}

	/**
	 * 
	 * @param propertyName
	 * @param pattern
	 */
	public void notLike(String propertyName, String pattern) {
		doNotLike(propertyName, pattern);
	}

	/**
	 * 
	 * @param propertyName
	 * @param number
	 */
	public void greaterThan(String propertyName, Number number) {
		doGreaterThan(propertyName, number);
	}

	/**
	 * 
	 * @param propertyName
	 * @param number
	 */
	public void greaterOrEqualTo(String propertyName, Number number) {
		doGreaterOrEqualTo(propertyName, number);
	}

	/**
	 * 
	 * @param propertyName
	 * @param number
	 */
	public void lessThan(String propertyName, Number number) {
		doLessThan(propertyName, number);
	}

	/**
	 * 
	 * @param propertyName
	 * @param number
	 */
	public void lessOrEqualTo(String propertyName, Number number) {
		doLessOrEqualTo(propertyName, number);
	}

	/**
	 * @param propertyName
	 * @param direction
	 */
	public void addOrder(String propertyName, Direction direction) {
		if (direction == Direction.ASC) {
			orderList.add(new Order(Direction.ASC, propertyName));
		} else {
			orderList.add(new Order(Direction.DESC, propertyName));
		}

		Sort sort = new Sort(orderList);
		setSort(sort);
	}
	
	/**
	 * 
	 * @param hasIdClass
	 */
	public void hasIdClass(boolean hasIdClass) {
		this.hasIdClass = hasIdClass;
	}

	/**
	 * 
	 * @param hasIdClass
	 * @param idPropertyName
	 */
	public void hasIdClass(boolean hasIdClass, String idPropertyName) {
		this.hasIdClass = hasIdClass;
		this.idPropertyName = idPropertyName;
	}

	/**
	 * @param propertyName
	 * @param number
	 */
	private void doLessOrEqualTo(String propertyName, Number number) {
		Assert.hasText(propertyName, "[doLessOrEqualTo] Property name must be provided.");
		Assert.notNull(number, "[doLessOrEqualTo] Number must be provided.");
		Expression<? extends Number> exp = getPath(propertyName, from);
		predicateList.add(criteriaBuilder.le(exp, number));
	}

	/**
	 * @param propertyName
	 * @param number
	 */
	private void doLessThan(String propertyName, Number number) {
		Assert.hasText(propertyName, "[doLessThan] Property name must be provided.");
		Assert.notNull(number, "[doLessThan] Number must be provided.");
		Expression<? extends Number> exp = getPath(propertyName, from);
		predicateList.add(criteriaBuilder.lt(exp, number));
	}

	/**
	 * @param propertyName
	 * @param number
	 */
	private void doGreaterOrEqualTo(String propertyName, Number number) {
		Assert.hasText(propertyName, "[doGreaterOrEqualTo] Property name must be provided.");
		Assert.notNull(number, "[doGreaterOrEqualTo] Number must be provided.");
		Expression<? extends Number> exp = getPath(propertyName, from);
		predicateList.add(criteriaBuilder.ge(exp, number));
	}

	/**
	 * @param propertyName
	 * @param number
	 */
	private void doGreaterThan(String propertyName, Number number) {
		Assert.hasText(propertyName, "[doGreaterThan] Property name must be provided.");
		Assert.notNull(number, "[doGreaterThan] Number must be provided.");
		Expression<? extends Number> exp = getPath(propertyName, from);
		predicateList.add(criteriaBuilder.gt(exp, number));
	}

	/**
	 * @param propertyName
	 * @param pattern
	 */
	private void doNotLike(String propertyName, String pattern) {
		Assert.hasText(propertyName, "[doNotLike] Property name must be provided.");
		Assert.hasText(pattern, "[doNotLike] Pattern must be provided.");
		predicateList.add(criteriaBuilder.like(getPath(propertyName, from).as(String.class), pattern).not());
	}

	/**
	 * @param propertyName
	 * @param pattern
	 */
	private void doLike(String propertyName, String pattern) {
		Assert.hasText(propertyName, "[doLike] Property name must be provided.");
		Assert.hasText(pattern, "[doLike] Pattern must be provided.");
		predicateList.add(criteriaBuilder.like(getPath(propertyName, from).as(String.class), pattern));
	}

	/**
	 * @param propertyName
	 * @param values
	 */
	private void doNotin(String propertyName, Collection<?> values) {
		Assert.hasText(propertyName, "[doNotin] Property name must be provided.");
		Assert.notEmpty(values, "[doNotin] Values must not be empty.");
		predicateList.add(getPath(propertyName, from).in(values).not());
	}

	/**
	 * @param propertyName
	 * @param values
	 */
	private void doIn(String propertyName, Collection<?> values) {
		Assert.hasText(propertyName, "[doIn] Property name must be provided.");
		Assert.notEmpty(values, "[doIn] Values must not be empty.");
		predicateList.add(getPath(propertyName, from).in(values));
	}

	/**
	 * 
	 * @param propertyName
	 */
	private void doIsNotNull(String propertyName) {
		Assert.hasText(propertyName, "[doIsNotNull] Property name must be provided.");
		predicateList.add(criteriaBuilder.isNotNull(getPath(propertyName, from)));
	}

	/**
	 * 
	 * @param propertyName
	 * @param value
	 */
	private void doNotEqual(String propertyName, Object value) {
		Assert.hasText(propertyName, "[doNotEqual] Property name must be provided.");
		Assert.notNull(value, "[doNotEqual] Value must not be null.");
		predicateList.add(criteriaBuilder.notEqual(getPath(propertyName, from), value));
	}

	/**
	 * 
	 * @param propertyName
	 * @param value
	 */
	private void doEqual(String propertyName, final Object value) {
		Assert.hasText(propertyName, "[doEqual] Property name must be provided.");
		Assert.notNull(value, "[doEqual] Value must not be null.");
		predicateList.add(criteriaBuilder.equal(getPath(propertyName, from), value));
	}

	public void or() {
		this.operator = BooleanOperator.OR;
	}

	/**
	 * 
	 * @param propertyName
	 */
	private void doIsNull(String propertyName) {
		Assert.hasText(propertyName, "[doIsNull] Property name must be provided.");
		predicateList.add(criteriaBuilder.isNull(getPath(propertyName, from)));
	}

	/**
	 * 
	 * @param propertyName
	 * @param root
	 * @return
	 */
	private <T, V> Path<V> getPath(String propertyName, Root<?> root) {
		if (propertyName.indexOf(".") == -1) {
			path = root.get(propertyName);
		} else {
			String[] properties = propertyName.split("\\.");

			if (hasIdClass) {
				// this is so we can assure that if there is an id class
				// if there is no id class checking it throws an exception
				if (propertyName.contains(idPropertyName)) {
					path = root.get(properties[0]);

					Path<?> tempPath = path;
					for (int i = 1; i < properties.length; i++) {
						tempPath = path.get(properties[i]);
					}

					path = tempPath;
				} else {
					if (properties.length == 2) {
						path = root.join(properties[0], JoinType.LEFT).get(properties[1]);
					} else {
						path = root.join(properties[0], JoinType.LEFT).join(properties[1], JoinType.LEFT)
								.get(properties[2]);
					}

				}
			} else {
				if (properties.length == 2) {
					path = root.join(properties[0], JoinType.LEFT).get(properties[1]);
				} else {
					path = root.join(properties[0], JoinType.LEFT).join(properties[1], JoinType.LEFT)
							.get(properties[2]);
				}
			}

		}

		return (Path<V>) path;
	}

	/**
	 * 
	 * @param entityManager
	 */
	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * 
	 * @return
	 */
	private EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 
	 * @param sort
	 */
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	/**
	 * 
	 * @return
	 */
	private Class getDomainClass() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class) parameterizedType.getActualTypeArguments()[0];
	}
}