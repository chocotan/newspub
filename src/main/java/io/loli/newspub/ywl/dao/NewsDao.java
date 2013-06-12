package io.loli.newspub.ywl.dao;

import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.entity.News;

import java.util.List;

public interface NewsDao {
	void save(News entity);

	void delete(News entity);

	void update(News enntity);

	News findById(int id);

	List<News> list();

	List<News> listPage(int startIndex, int maxCount);

	List<News> listByCat(Category cat);

	List<News> listPageByCat(Category cat, int startIndex, int maxCount);

	int count();
	int countByCat(Category cat);
}