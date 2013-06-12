package io.loli.newspub.ywl.service.impl;

import io.loli.newspub.ywl.dao.CategoryDao;
import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.service.CategoryService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("catService")
@Singleton
public class CategoryServiceImpl implements CategoryService {
	@Inject
	@Named("catDao")
	private CategoryDao categoryDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Category cat) {
		categoryDao.save(cat);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Category cat) {
		categoryDao.update(cat);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Category cat) {
		categoryDao.delete(cat);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Category findById(int id) {
		return categoryDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> list() {
		return categoryDao.list();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Category> listPage(int startIndex, int maxCount) {
		return categoryDao.listPage(startIndex, maxCount);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(int id) {
		this.delete(this.findById(id));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteByIds(int[] ids) {
		for (int id : ids) {
			this.deleteById(id);
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int count() {
		return categoryDao.count();
	}
}
