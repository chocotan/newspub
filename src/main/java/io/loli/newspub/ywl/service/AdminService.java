package io.loli.newspub.ywl.service;

import io.loli.newspub.ywl.entity.Admin;

import java.util.List;

public interface AdminService {

	void save(Admin admin);

	void delete(Admin admin);

	void update(Admin admin);

	List<Admin> list();

	List<Admin> listPage(int startIndex, int maxCount);

	boolean isExist(String name);

	void deleteById(int id);

	Admin loginAuth(Admin admin);

	Admin findById(int id);

	int count();

	Admin findByName(String name);

}