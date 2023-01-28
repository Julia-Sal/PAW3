package code;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.jsf.dao.ClassDAO;
import com.jsf.dao.UserDAO;
import com.jsf.dao.UserRoleDAO;
import com.jsf.entities.User;

@Named
@RequestScoped
public class Admin {
	private static final String PAGE_ADD_USER = "/pages/admin/addUser?faces-redirect=true";
	private static final String PAGE_EDIT_USER = "/pages/admin/editUser?faces-redirect=true";
	
	private String name;
	private String surname;
	private String password;
	private String login;
	private String roleName;
	private String className;
	private List<String> roleList;
	private List<String> classList;
	private String variable;
	private User selectedUser;
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	ClassDAO classDAO;

	@Inject
	UserRoleDAO userRoleDAO;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public List<String> getRoleList() {//znajdz wszystkie role (bez administratora)
		roleList = classDAO.findRoleNames();
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public List<String> getClassList() {//znajdz wszystkie klasy
		classList = classDAO.findAllClassNames();
		return classList;
	}
	public void setClassList(List<String> classList) {
		this.classList = classList;
	}
	
	
	public DataModel<Object[]> getList(AjaxBehaviorEvent event){//wyświetl listę użytkowników z daną rolą
		List<User> userList = userDAO.getUsersByRole(roleName, variable);
		
		List<Object[]> data = new ArrayList<>();
		for(int i=0;i<userList.size();i++) {
		    data.add(new Object[] {userList.get(i).getName(),userList.get(i).getSurname()});
		}
		DataModel<Object[]> userDataModel = new ListDataModel<Object[]>(data);
		
		return userDataModel;
	}
	
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	//Dodawanie użytkowników
	public String showAddUser() {
		return PAGE_ADD_USER;
	}
	
	public void addUser() {//pobierz dane z form i utwórz użytkownika o takich danych i przydziel mu rolę i klasę
		
		try {
		userDAO.addNewUserToDatabase(name, surname, login, password, roleName, className);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Dodano nowego użytkownika."));
		}catch(Exception e) 
		{FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Nie udało się dodać nowego użytkownika."));}
	}
	
	//Edytowanie użytkowników
	public String showEditUser() {
		return PAGE_EDIT_USER;
	}
	
	public void editUser(User selectedUser) {System.out.println("EDIT");}
	
	//usuwanie użytkowników
	public void deleteUser() {System.out.println("DELETE");}
	
	public void delete() {//
	    userDAO.delete(selectedUser);
	} 
	
	
	public void setSelectedUser(User selectedUser) {//pobieranie usera z tabeli
	    this.selectedUser = selectedUser;
	}

	
}
