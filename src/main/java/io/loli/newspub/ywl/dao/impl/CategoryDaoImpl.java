package io.loli.newspub.ywl.dao.impl;

import io.loli.newspub.ywl.dao.CategoryDao;
import io.loli.newspub.ywl.entity.Category;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("catDao")
@Singleton
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Category findById(int id) {
		return em.find(Category.class, id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> list() {
		return em.createNamedQuery("Category.listCategory", Category.class)
				.getResultList();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> listPage(int startIndex, int maxCount) {
		return em.createNamedQuery("Category.listCategory", Category.class)
				.setFirstResult(startIndex).setMaxResults(maxCount)
				.getResultList();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int count() {
		return ((Number) em.createNamedQuery("Category.countCat")
				.getSingleResult()).intValue();
	}

}
