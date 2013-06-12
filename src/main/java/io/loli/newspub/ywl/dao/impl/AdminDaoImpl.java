package io.loli.newspub.ywl.dao.impl;

import io.loli.newspub.ywl.dao.AdminDao;
import io.loli.newspub.ywl.entity.Admin;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("adminDao")
@Singleton
public class AdminDaoImpl extends BaseDao<Admin> implements AdminDao {

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Admin findById(int id) {
		return em.find(Admin.class, id);
	}

	@Override
	public List<Admin> list() {
		return em.createNamedQuery("Admin.listAdmin", Admin.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Admin> listPage(int startIndex, int maxCount) {
		return em.createNamedQuery("Admin.listAdmin", Admin.class)
				.setFirstResult(startIndex).setMaxResults(maxCount)
				.getResultList();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Admin findByName(String name) {
		return em.createNamedQuery("Admin.findAdminByName", Admin.class)
				.setParameter("name", name).getSingleResult();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int count() {
		return ((Number) em.createNamedQuery("Admin.countAdmin")
				.getSingleResult()).intValue();
	}
}
