package code;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Grade {
//wyświetlanie ocen
private static final String PAGE_GRADE = "grade?faces-redirect=true";
	
	public String showGradePage(){
		//flash.put("person", person);
		return PAGE_GRADE;
	}
//obliczanie średniej
	
//dodawanie ocen
	
//usuwanie ocen
	
//edytowanie ocen
	
}
