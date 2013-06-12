package io.loli.newspub.ywl.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@NamedQueries(value = {
		@NamedQuery(name = "Category.listCategory", query = "SELECT c FROM Category c"),
		@NamedQuery(name = "Category.countCat", query = "SELECT COUNT(c) FROM Category c") })
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int id;

	@Column(nullable = false, length = 45)
	private String name;

	// bi-directional many-to-one association to News
	@OneToMany(mappedBy = "category", cascade = { CascadeType.REFRESH,CascadeType.PERSIST })
	private List<News> news;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<News> getNews() {
		return this.news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public News addNews(News news) {
		getNews().add(news);
		news.setCategory(this);

		return news;
	}

	public News removeNews(News news) {
		getNews().remove(news);
		news.setCategory(null);

		return news;
	}

}