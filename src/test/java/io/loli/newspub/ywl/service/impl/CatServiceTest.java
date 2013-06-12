package io.loli.newspub.ywl.service.impl;

import io.loli.newspub.ywl.entity.Category;
import io.loli.newspub.ywl.service.CategoryService;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/root-context.xml")
public class CatServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Inject
	@Named("catService")
	private CategoryService catService;
	private Category cat;

	@Before
	public void setUp() {
		cat = new Category();
		cat.setName("国内新闻");
	}

	@Test
	public void testSave() {
		catService.save(cat);
		assertThat(cat.getId(), not(0));
	}

	@Test
	public void testDelete() {
		catService.save(cat);
		catService.deleteById(cat.getId());
		assertEquals(null, catService.findById(cat.getId()));
	}

	@Test
	public void count() {
		catService.save(cat);
		assertThat(catService.count(), not(0));
	}
}
