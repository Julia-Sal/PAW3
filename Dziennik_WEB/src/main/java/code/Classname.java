
package code;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Classname {
	private static final String PAGE_TIMETABLE = "pages/other/timetable?faces-redirect=true";
	
	public String showTimetablePage(){
		return PAGE_TIMETABLE;
	}
}
