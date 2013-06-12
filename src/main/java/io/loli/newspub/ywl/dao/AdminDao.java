package io.loli.newspub.ywl.dao;

import io.loli.newspub.ywl.entity.Admin;

import java.util.List;

public interface AdminDao {
	void save(Admin entity);

	void delete(Admin entity);

	void update(Admin entity);

	Admin findById(int id);

	List<Admin> list();

	List<Admin> listPage(int startIndex, int maxCount);

	Admin findByName(String name);

	int count();
}