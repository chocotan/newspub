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
 * The persistent class for the admin database table.
 * 
 */
@Entity
@Table(name = "admin")
@NamedQueries(value = {
		@NamedQuery(name = "Admin.listAdmin", query = "SELECT a FROM Admin a"),
		@NamedQuery(name = "Admin.countAdmin", query = "SELECT COUNT(a) FROM Admin a"),
		@NamedQuery(name = "Admin.findAdminByName", query = "SELECT a FROM Admin a WHERE a.username=:name")})
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int id;

	@Column(nullable = false, length = 45)
	private String password;

	@Column(nullable = false, length = 45)
	private String username;

	// bi-directional many-to-one association to news
	@OneToMany(mappedBy = "admin",cascade={CascadeType.ALL})
	private List<News> news;

	public Admin() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<News> getnews() {
		return this.news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public News addnews(News news) {
		getnews().add(news);
		news.setAdmin(this);

		return news;
	}

	public News removenews(News news) {
		getnews().remove(news);
		news.setAdmin(null);

		return news;
	}

}