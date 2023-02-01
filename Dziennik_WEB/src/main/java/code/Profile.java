package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.ListDataModel;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.PrimeFaces;

import com.jsf.dao.UserDAO;
import com.jsf.dao.ClassDAO;
import com.jsf.entities.User;
import com.jsf.entities.Class;

@Named
@RequestScoped
public class Profile {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_ADMIN = "/pages/admin/admin?faces-redirect=true";
	private static final String PAGE_TEACHER = "/pages/teacher/teacher?faces-redirect=true";
	private static final String PAGE_STUDENT = "/pages/student/student?faces-redirect=true";
	private static final String PAGE_PARENT = "/pages/parent/parent?faces-redirect=true";
	private static final String PAGE_LIST = "/pages/other/classList?faces-redirect=true";
	
	private String role;
	private String className;
	private String subject;
	private int lastGrade;
	private ListDataModel<Object[]> userDataModel;
	private String newPassword;
	private String confPassword;
	private String password;
	
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
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public String showProfile(String role) {//wyświetlanie profilu
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
	
	public List<String> fillUserData(RemoteClient<User> client, UserDAO userDAO) {//wypełnij dane usera
		if(client.getRoles().toString()!="admin") {
			List<String> classNameList = userDAO.getClassNameByUserID(client.getDetails().getIdUser());
			return classNameList;		
		}else {
			return null;}
	}
	
	public String showClassList() {//wyświetl listę osób w klasie
		return PAGE_LIST;
	}
	
	
	public void showSidebar() {
		PrimeFaces.current().executeScript("PF('sidebar5').toggle();");
	}
	
	public void changePassword(String login) {
		String currentPassword = userDAO.getUserByLogin(login).getPassword();
		if(currentPassword.equals(password)) {
			if(newPassword.equals(confPassword)) {
				//ewentualnie jakiś REGEX
				userDAO.changePassword(newPassword, login);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Hasło zostało zmienione"));
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Podane hasła nie są takie same"));
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Podane hasło jest nieprawidłowe"));
		}
	}
	
}
