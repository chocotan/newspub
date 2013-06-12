package io.loli.newspub.ywl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao<T> {
	@PersistenceContext
	protected EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(T entity) {
		em.persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T entity) {
		em.merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T entity) {
		em.remove(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	protected abstract T findById(int id);

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	protected abstract List<T> list();
}
