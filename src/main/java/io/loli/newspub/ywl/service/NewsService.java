package io.loli.newspub.ywl.service;

import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.entity.News;

import java.util.List;

public interface NewsService {

	void save(News news);

	void update(News news);

	void delete(News news);

	News findById(int id);

	void deleteById(int id);

	void deleteByIds(int[] ids);

	List<News> list();

	List<News> listByCat(Category cat);

	List<News> listPage(int startIndex, int maxCount);

	List<News> listPageByCat(Category cat, int startIndex, int maxCount);

	int count();

	int countByCat(Category cat);

	int countByCatId(int catId);

}