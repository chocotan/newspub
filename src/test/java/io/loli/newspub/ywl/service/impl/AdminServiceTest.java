package io.loli.newspub.ywl.service.impl;

import io.loli.newspub.ywl.entity.Admin;
import io.loli.newspub.ywl.service.AdminService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.not;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/root-context.xml")
public class AdminServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private Admin admin;
	@Inject
	@Named("adminService")
	private AdminService adminService;

	@Before
	public void setUp() {
		admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("admin");
	}

	@Test
	public void testSave() {
		adminService.save(admin);
		assertThat(admin.getId(), not(0));
	}

	@Test
	public void testLoginFailed() {
		adminService.save(admin);
		Admin nadmin = new Admin();
		nadmin.setUsername(admin.getUsername());
		admin.setPassword("password");
		assertEquals(null, adminService.loginAuth(nadmin));
	}

	@Test
	public void testFindByName() {
		adminService.save(admin);
		assertEquals(admin, adminService.findByName(admin.getUsername()));
	}

	@Test
	public void testLoginSuccess() {
		adminService.save(admin);
		assertEquals(admin, adminService.loginAuth(admin));
	}

	@Test
	public void testExist() {
		adminService.save(admin);
		assertTrue(adminService.isExist(admin.getUsername()));
	}
}
