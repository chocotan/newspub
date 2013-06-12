package io.loli.newspub.ywl.service.impl;

import io.loli.newspub.ywl.dao.NewsDao;
import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.entity.News;
import io.loli.newspub.ywl.service.CategoryService;
import io.loli.newspub.ywl.service.NewsService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("newsService")
@Singleton
public class NewsServiceImpl implements NewsService {
	@Inject
	@Named("newsDao")
	private NewsDao newsDao;

	@Inject
	@Named("catService")
	private CategoryService catService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(News news) {
		newsDao.save(news);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(News news) {
		newsDao.update(news);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(News news) {
		newsDao.delete(news);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public News findById(int id) {
		return newsDao.findById(id);
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
	public List<News> list() {
		return newsDao.list();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> listByCat(Category cat) {
		return newsDao.listByCat(cat);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> listPage(int startIndex, int maxCount) {
		return newsDao.listPage(startIndex, maxCount);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> listPageByCat(Category cat, int startIndex, int maxCount) {
		return newsDao.listPageByCat(cat, startIndex, maxCount);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int count() {
		return newsDao.count();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int countByCat(Category cat) {
		return newsDao.countByCat(cat);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int countByCatId(int catId) {
		return this.countByCat(catService.findById(catId));
	}
}
