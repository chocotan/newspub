package io.loli.newspub.ywl.dao;

import io.loli.newspub.ywl.entity.Category;

import java.util.List;

public interface CategoryDao {

	void save(Category entity);

	void delete(Category entity);

	void update(Category enntity);

	Category findById(int id);

	List<Category> list();

	List<Category> listPage(int startIndex, int maxCount);

	int count();
}