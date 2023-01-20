package code;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Named
@RequestScoped
public class Login {
	
	private static final String PAGE_ADMIN = "/pages/admin/admin?faces-redirect=true";
	private static final String PAGE_TEACHER = "/pages/teacher/teacher?faces-redirect=true";
	private static final String PAGE_STUDENT = "/pages/student/student?faces-redirect=true";
	private static final String PAGE_PARENT = "/pages/parent/parent?faces-redirect=true";
	private static final String PAGE_LOGIN = "/pages/login?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private String login;
	private String password;
	
	@Inject
	UserDAO userDAO;
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		User user = userDAO.getUserFromDatabase(login, password);

		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub hasło", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		else{
			RemoteClient<User> client = new RemoteClient<User>(); //create new RemoteClient
			client.setDetails(user);
			List<String> roles = userDAO.getRolesByLogin(user);//get User roles
			
			if (roles != null) { //save roles in RemoteClient
				for (String role: roles) {
					client.getRoles().add(role);
					
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
		            }
				}
			}
			return PAGE_STAY_AT_THE_SAME;
		}
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);	
		session.invalidate();
		return PAGE_LOGIN;
	}
}


