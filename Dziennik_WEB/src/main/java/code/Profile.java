package code;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Named;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jsf.entities.User;

@Named
@RequestScoped
public class Profile {
	
	private static final String PAGE_ADMIN = "/pages/admin/admin?faces-redirect=true";
	private static final String PAGE_TEACHER = "/pages/teacher/teacher?faces-redirect=true";
	private static final String PAGE_STUDENT = "/pages/student/student?faces-redirect=true";
	private static final String PAGE_PARENT = "/pages/parent/parent?faces-redirect=true";
	
	
//wyświetlanie profilu
	public String showProfile() {
		/*
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
		
		if (role.equals("student")){
            return PAGE_STUDENT;
       } else if (role.equals("teacher")){
           return PAGE_TEACHER;
        } else if (role.equals("admin")){
            return PAGE_ADMIN;
        } else if (role.equals("parent")){
            return PAGE_PARENT;
        }else {
        	return null;}*/
	return null;
	}
	
//edycja na stronie ???
}
