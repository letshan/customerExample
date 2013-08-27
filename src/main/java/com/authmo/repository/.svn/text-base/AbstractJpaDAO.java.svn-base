package com.authmo.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 

@Repository
public abstract class AbstractJpaDAO<T extends Serializable> {

	private Class<T> clazz;
	@PersistenceContext
	private EntityManager entityManager;

	 
	public AbstractJpaDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T getById(final Object id) {
		if (id != null) {
			return (T) getEntityManager().find(clazz, id);
		} else {
			return null;
		}
	}

	public List<T> getAll() {
		return getEntityManager().createQuery("from " + clazz.getName())
				.getResultList();
	}
	@Transactional( readOnly = false)
	public void create(final T entity) {
		if (entity != null) {
			getEntityManager().persist(entity);
		}
	}
	@Transactional( readOnly = false)
	public T update(final T entity) {
		if (entity != null) {
			return (T) getEntityManager().merge(entity);
		} else {
			return null;
		}
	}
	@Transactional( readOnly = false) 
	public void delete(final T entity) {
		if (entity != null) {
			getEntityManager().remove(entity);
		}
	}
	@Transactional( readOnly = false)
	public void deleteById(final Long entityId) {
		final T entity = getById(entityId);
		if (entity != null) {
			delete(entity);
		}
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
