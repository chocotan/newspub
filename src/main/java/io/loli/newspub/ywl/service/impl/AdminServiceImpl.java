package io.loli.newspub.ywl.service.impl;

import io.loli.newspub.ywl.dao.AdminDao;
import io.loli.newspub.ywl.entity.Admin;
import io.loli.newspub.ywl.service.AdminService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Named("adminService")
@Singleton
public class AdminServiceImpl implements AdminService {
	@Inject
	@Named("adminDao")
	private AdminDao adminDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Admin admin) {
		adminDao.save(admin);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Admin admin) {
		adminDao.delete(admin);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Admin admin) {
		adminDao.update(admin);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Admin> list() {
		return adminDao.list();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Admin findById(int id) {
		return adminDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void deleteById(int id) {
		this.delete(this.findById(id));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Admin> listPage(int startIndex, int maxCount) {
		return adminDao.listPage(startIndex, maxCount);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Admin findByName(String name){
		return adminDao.findByName(name);
	}

	/**
	 * 判断此用户名是否存在
	 * 
	 * @param name
	 *            需要判断的用户名
	 * @return 存在是true 不存在则是false
	 */
	@Override
	@SuppressWarnings("finally")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean isExist(String name) {
		boolean isexists = false;
		try {
			this.findByName(name);
			isexists = true;
		} catch (Exception e) {
			isexists = false;
		} finally {
			return isexists;
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param admin
	 * @return 如果成功登陆则返回此admin, 否则返回null
	 */
	@Override
	@SuppressWarnings("finally")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Admin loginAuth(Admin admin) {
		Admin result = null;
		try {
			result = adminDao.findByName(admin.getUsername());
			// 判断密码是否相等, 如果不相等, 将result置空
			if (result.getPassword().equals(admin.getPassword())) {
			} else {
				result = null;
			}
		} catch (Exception e) {
		} finally {
			return result;
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int count() {
		return adminDao.count();
	}
}
