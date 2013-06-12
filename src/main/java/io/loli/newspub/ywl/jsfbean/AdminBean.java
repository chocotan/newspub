package io.loli.newspub.ywl.jsfbean;

import io.loli.newspub.ywl.entity.Admin;
import io.loli.newspub.ywl.jsr330.SessionScoped;
import io.loli.newspub.ywl.service.AdminService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
//@Scope("session")
@SessionScoped
public class AdminBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private Admin admin;
	
	@PostConstruct
	public void init(){
		admin = new Admin();
	}
	
	//@ManagedProperty(value = "#{adminService}")
	@Inject
	@Named("adminService")
	private AdminService adminService;

	public String login() {
		Admin loggedAdmin = adminService.loginAuth(admin);
		if (loggedAdmin == null) {
			return "login";
		} else {
			admin = loggedAdmin;
			return "catList";
		}
	}

	public String logout() {
		init();
		return "logoutSuccess";
	}

	@SuppressWarnings("finally")
	public String update() {
		try {
			adminService.update(admin);
		} catch (Exception e) {
			return "editAdminFail";
		} finally {
			return "editAdminSuccess";
		}
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}
