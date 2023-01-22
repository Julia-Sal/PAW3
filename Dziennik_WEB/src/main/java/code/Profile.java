package code;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

@Named
@RequestScoped
public class Profile {
	
	private static final String PAGE_ADMIN = "/pages/admin/admin?faces-redirect=true";
	private static final String PAGE_TEACHER = "/pages/teacher/teacher?faces-redirect=true";
	private static final String PAGE_STUDENT = "/pages/student/student?faces-redirect=true";
	private static final String PAGE_PARENT = "/pages/parent/parent?faces-redirect=true";

	
	private String role;
	private String className;
	private String subject;
	private int lastGrade;
	
	@Inject
	UserDAO userDAO;
	
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassName() {
		return className;
	}
	
	public void setLastGrade(int lastGrade) {
		this.lastGrade = lastGrade;
	}
	public int getLastGrade() {
		return lastGrade;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
	}
	
	public void TEST() {
		System.out.println(this.userDAO);
	}
//wyświetlanie profilu
	public String showProfile(String role, RemoteClient<User> client, UserDAO userDAO) {
		
		Profile profile = new Profile();
		
		profile.fillUserData(client, userDAO);
		
		
		if (role.equals("student")){
            return PAGE_STUDENT;
        } else if (role.equals("teacher")){
           return PAGE_TEACHER;
        } else if (role.equals("admin")){
            return PAGE_ADMIN;
        } else if (role.equals("parent")){
            return PAGE_PARENT;
        } else {
        	return null;}
	}
	
//wypełnij dane usera	
	public void fillUserData(RemoteClient<User> client, UserDAO userDAO) {
		Profile profile = new Profile();
		
		if(client.getRoles().toString()!="admin") {
		
		List<String> classNameList = userDAO.getClassNameByUserID(client.getDetails().getIdUser());
		System.out.println(classNameList);
		profile.setClassName(classNameList.toString());
		
		System.out.println(className);
		}
	}
//edycja na stronie ???
}
