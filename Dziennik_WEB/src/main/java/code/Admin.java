package code;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.jsf.dao.ClassDAO;
import com.jsf.dao.UserDAO;
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
	private List<String> roleList;
	private List<String> classList;
	private String variable;
	private User selectedUser;
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	ClassDAO classDAO;

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
		password = "PASSWORD123";
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
	
	public List<String> getRoleList() {//znajdz wszystkie role (bez administratora)
		roleList = classDAO.findRoleNames();
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public List<String> getClassList() {//znajdz wszystkie klasy
		classList = classDAO.findAllClassNames();
		classList.add(null);
		return classList;
	}
	public void setClassList(List<String> classList) {
		this.classList = classList;
	}
	
	
	public ListDataModel<Object[]> getList(String roleName){//wyświetl listę użytkowników z daną rolą
		System.out.println(roleName);
		//String roleName = "student";
		
		List<User> userList = userDAO.getUsersByRole(roleName, variable);
		
		List<Object[]> data = new ArrayList<>();
		for(int i=0;i<userList.size();i++) {
		    data.add(new Object[] {userList.get(i).getName(),userList.get(i).getSurname()});
		}
		ListDataModel<Object[]> userDataModel = new ListDataModel<Object[]>(data);
		
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
	
	public String addUser() {
		//pobierz dane z form i utwórz użytkownika o takich danych
		//System.out.println(user.getName());
		System.out.println("ANANAN");
		//userDAO.insert(user);
		
		//przydziel rolę użytkownikowi
		
		return "ALA";
		//jeśli jest studentem to dodaj go do klasy
		
		
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
