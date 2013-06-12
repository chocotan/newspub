package io.loli.newspub.ywl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the news database table.
 * 
 */
@Entity
@Table(name = "news")
@NamedQueries(value = {
		@NamedQuery(name = "News.listNews", query = "SELECT n FROM News n order by n.pubDate desc"),
		@NamedQuery(name = "News.listNewsByCat", query = "SELECT n FROM News n where n.category=:cat order by n.pubDate desc"),
		@NamedQuery(name = "News.countNews", query = "SELECT COUNT(n) FROM News n"),
		@NamedQuery(name = "News.countNewsByCat", query = "SELECT COUNT(n) FROM News n where n.category=:cat ")})
public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private int id;

	@Column(nullable = false, length = 20000)
	private String content;

	private Date pubDate;

	@Column(nullable = false, length = 45)
	private String title;

	// bi-directional many-to-one association to Admin
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name = "a_id", nullable = false)
	private Admin admin;

	// bi-directional many-to-one association to Category
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name = "c_id", nullable = false)
	private Category category;

	public News() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPubDate() {
		return this.pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}