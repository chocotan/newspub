package io.loli.newspub.ywl.jsfbean;

import io.loli.newspub.ywl.entity.Admin;
import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.entity.News;
import io.loli.newspub.ywl.jsr330.SessionScoped;
import io.loli.newspub.ywl.service.AdminService;
import io.loli.newspub.ywl.service.CategoryService;
import io.loli.newspub.ywl.service.NewsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class NewsBean {
	private News news;
	private List<News> newsList;
	private final static int MAXCOUNT = 10;
	private Map<Integer, Boolean> deleted;

	private int count;

	@PostConstruct
	public void init() {
		news = new News();
		news.setAdmin(new Admin());
		news.setCategory(new Category());
	}

	@Inject
	@Named("newsService")
	private NewsService newsService;
	@Inject
	@Named("catService")
	private CategoryService catService;
	@Inject
	@Named("adminService")
	private AdminService adminService;
	@Inject
	private AdminBean adminBean;

	public String add() {
		if (news == null) {
			news = new News();
			news.setAdmin(new Admin());
			news.setCategory(new Category());
		}
		news.setCategory(catService.findById(news.getCategory().getId()));
		news.setPubDate(new java.util.Date());
		news.setAdmin(adminBean.getAdmin());
		newsService.save(news);
		this.init();
		return "list";
	}

	public String delete() {
		for (Object obj : deleted.entrySet().toArray()) {
			@SuppressWarnings("unchecked")
			Entry<Integer, Boolean> entry = (Entry<Integer, Boolean>) obj;
			if (!entry.getValue()) {
				deleted.remove(entry.getKey());
			}
		}
		int[] ids = new int[deleted.size()];
		int count = 0;
		for (int id : deleted.keySet()) {
			ids[count++] = id;
		}
		newsService.deleteByIds(ids);
		return "list";
	}

	public String update() {
		/*
		 * news.setCategory(catService.findById(news.getCategory().getId()));
		 * news.setAdmin(adminService.findById(news.getAdmin().getId()));
		 */
		newsService.update(news);
		this.init();
		return "list";
	}

	public void initNews(int id) {
		news = newsService.findById(id);
	}

	public List<News> listPage(int c_id, int page) {
		if (page == 0)
			page = 1;
		if (deleted == null) {
			deleted = new HashMap<Integer, Boolean>();
		}
		List<News> newsList;
		if (c_id == 0) {
			newsList = newsService.listPage((page - 1) * MAXCOUNT, MAXCOUNT);
		} else {
			newsList = newsService.listPageByCat(catService.findById(c_id),
					(page - 1) * MAXCOUNT, MAXCOUNT);
		}
		for (News news : newsList) {
			deleted.put(news.getId(), false);
		}
		return newsList;
	}

	public List<Integer> pageList() {
		int ind = newsService.count();
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= (ind - 1) / MAXCOUNT + 1; i++) {
			pages.add(i);
		}
		count = pages.size();
		return pages;
	}

	public List<Integer> pageList(int c_id) {
		if (c_id == 0) {
			return pageList();
		}
		int ind = newsService.countByCatId(c_id);
		List<Integer> pages = new ArrayList<Integer>();
		for (int i = 1; i <= (ind - 1) / MAXCOUNT + 1; i++) {
			pages.add(i);
		}
		count = pages.size();
		return pages;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public Map<Integer, Boolean> getDeleted() {
		return deleted;
	}

	public void setDeleted(Map<Integer, Boolean> deleted) {
		this.deleted = deleted;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
