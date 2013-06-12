package io.loli.newspub.ywl.service;

import io.loli.newspub.ywl.entity.Category;

import java.util.List;

public interface CategoryService {

	void save(Category cat);

	void update(Category cat);

	void delete(Category cat);

	Category findById(int id);

	List<Category> list();

	List<Category> listPage(int startIndex, int maxCount);

	void deleteById(int id);

	void deleteByIds(int[] ids);

	int count();

}