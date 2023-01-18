
package code;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Classname {
	private static final String PAGE_TIMETABLE = "timetable?faces-redirect=true";
	
	public String showTimetablePage(){
		//flash.put("person", person);
		return PAGE_TIMETABLE;
	}
}
