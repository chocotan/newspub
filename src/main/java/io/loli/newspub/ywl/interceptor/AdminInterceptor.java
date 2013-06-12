package io.loli.newspub.ywl.interceptor;

import io.loli.newspub.ywl.jsfbean.AdminBean;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AdminInterceptor implements PhaseListener {
	private static final long serialVersionUID = 9197901884903833575L;
	@Inject
	@Named("adminBean")
	private AdminBean adminBean;

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return null;
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		// Check to see if they are on the login page.
		boolean loginPage = fc.getViewRoot().getViewId().lastIndexOf("login") > -1 ? true
				: false;
		if (!loginPage && !loggedIn()) {
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "logout");
		}
	}

	// 验证是否已经登陆
	private boolean loggedIn() {
		return adminBean.getAdmin() != null;
	}
}