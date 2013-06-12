package io.loli.newspub.ywl.service.impl;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import io.loli.newspub.ywl.entity.Admin;
import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.entity.News;
import io.loli.newspub.ywl.service.AdminService;
import io.loli.newspub.ywl.service.CategoryService;
import io.loli.newspub.ywl.service.NewsService;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/root-context.xml")
public class NewsServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private News news;
	private Category cat;
	@Inject
	@Named("newsService")
	private NewsService newsService;
	@Inject
	@Named("catService")
	private CategoryService catService;
	@Inject
	@Named("adminService")
	private AdminService adminService;

	@Before
	public void setUp() {
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("admin");
		adminService.save(admin);
		cat = new Category();
		cat.setName("国内新闻");
		catService.save(cat);
		news = new News();
		news.setTitle("我是好人");
		news.setContent("大家好");
		news.setAdmin(admin);
		news.setPubDate(new java.util.Date());
		news.setCategory(cat);
	}

	@Test
	public void testSave() {
		newsService.save(news);
		assertThat(news.getId(), not(0));
	}
	
	@Test
	public void testFindByCat(){
		newsService.save(news);
		assertThat(newsService.findById(news.getId()).getId(),not(0));
	}
}
