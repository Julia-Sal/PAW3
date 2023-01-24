package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.ListDataModel;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.datatable.DataTable;

import com.jsf.dao.UserDAO;
import com.jsf.dao.ClassDAO;
import com.jsf.entities.User;
import com.jsf.entities.Class;

@Named
@RequestScoped
public class Profile {
	
	private static final String PAGE_ADMIN = "/pages/admin/admin?faces-redirect=true";
	private static final String PAGE_TEACHER = "/pages/teacher/teacher?faces-redirect=true";
	private static final String PAGE_STUDENT = "/pages/student/student?faces-redirect=true";
	private static final String PAGE_PARENT = "/pages/parent/parent?faces-redirect=true";
	private static final String PAGE_LIST = "/pages/other/classList?faces-redirect=true";
	
	private String role;
	private String className;
	private String subject;
	private int lastGrade;
	//private DataTable dataTable = new DataTable();
	private ListDataModel<Object[]> userDataModel;
	@Inject
	UserDAO userDAO;
	
	@Inject
	ClassDAO classDAO;
	
	@Inject
	Flash flash;
	
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
	
	public ListDataModel<Object[]> getUserDataModel() {
		return userDataModel;
	}
	public void setUserDataModel(ListDataModel<Object[]> userDataModel) {
		this.userDataModel = userDataModel;
	}
	
	public ListDataModel<Object[]> getList(String className){	
		List<String> userListNames = classDAO.getClassListName(className);
		List<String> userListSurnames = classDAO.getClassListSurname(className);
		
		List<Object[]> data = new ArrayList<>();
		for(int i=0;i<userListNames.size();i++) {
		    data.add(new Object[] {userListNames.get(i),userListSurnames.get(i)});
		}
		ListDataModel<Object[]> userDataModel = new ListDataModel<Object[]>(data);
		
		return userDataModel;
	}
	
	
//wyświetlanie profilu
	public String showProfile(String role) {
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
	public List<String> fillUserData(RemoteClient<User> client, UserDAO userDAO) {
		if(client.getRoles().toString()!="admin") {
			List<String> classNameList = userDAO.getClassNameByUserID(client.getDetails().getIdUser());
			return classNameList;		
		}else {
			return null;}
	}
	
//wyświetl listę osób w klasie
	public String showClassList() {
		return PAGE_LIST;
	}
	
	
	
	
}
