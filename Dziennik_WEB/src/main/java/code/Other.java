package code;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jsf.dao.RoleDAO;

@Named
@RequestScoped
public class Other {
	private static final String PAGE_TIMETABLE = "/pages/other/timetable?faces-redirect=true";
	
	@Inject
	RoleDAO roleDAO;
	
	public String showTimetablePage(){
		return PAGE_TIMETABLE;
	}
	

	}
