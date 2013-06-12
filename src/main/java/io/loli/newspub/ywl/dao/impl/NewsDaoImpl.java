package io.loli.newspub.ywl.dao.impl;

import io.loli.newspub.ywl.dao.NewsDao;
import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.entity.News;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("newsDao")
@Singleton
public class NewsDaoImpl extends BaseDao<News> implements NewsDao {

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public News findById(int id) {
		return em.find(News.class, id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> list() {
		return em.createNamedQuery("News.listNews", News.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> listPage(int startIndex, int maxCount) {
		return em.createNamedQuery("News.listNews", News.class)
				.setFirstResult(startIndex).setMaxResults(maxCount)
				.getResultList();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> listByCat(Category cat) {
		return em.createNamedQuery("News.listNewsByCat", News.class).setParameter("cat", cat)
				.getResultList();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<News> listPageByCat(Category cat, int startIndex, int maxCount) {
		return em.createNamedQuery("News.listNewsByCat", News.class).setParameter("cat", cat)
				.setFirstResult(startIndex).setMaxResults(maxCount)
				.getResultList();
	}

	@Override
	public int count() {
		return ((Number) em.createNamedQuery("News.countNews")
				.getSingleResult()).intValue();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int countByCat(Category cat) {
		return ((Number) em.createNamedQuery("News.countNewsByCat").setParameter("cat", cat)
				.getSingleResult()).intValue();
	}
}
