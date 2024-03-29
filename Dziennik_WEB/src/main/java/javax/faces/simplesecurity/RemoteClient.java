package javax.faces.simplesecurity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class RemoteClient<T> {

	private String login;
	private String pass;
	private String name;
	private String className;
	private String remoteAddr;
	private String remoteHost;
	private int remotePort;
	private T details;

	private List<String> roles = new ArrayList<String>();
	private List<String> classNames = new ArrayList<String>();
	
	public RemoteClient() {
	}

	public RemoteClient(ServletRequest request) {
		this.remoteAddr = request.getRemoteAddr();
		this.remoteHost = request.getRemoteHost();
		this.remotePort = request.getRemotePort();
	}

	public RemoteClient(String login, String pass, String name, ServletRequest request) {
		this.login = login;
		this.pass = pass;
		this.name = name;
		if (request != null) {
			this.remoteAddr = request.getRemoteAddr();
			this.remoteHost = request.getRemoteHost();
			this.remotePort = request.getRemotePort();			
		}
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public int getRemotePort() {
		return remotePort;
	}
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public T getDetails() {
		return details;
	}
	public void setDetails(T details) {
		this.details = details;
	}

	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public List<String> getClassNames() {
		return classNames;
	}
	public void setClassNames(List<String> classNames) {
		this.classNames = classNames;
	}

	public boolean isInRole(String role) {
		return roles.contains(role);
	}
	public boolean isInOneRole(HashSet<String> roles) {
		boolean found = false;
		for (String role : roles) {
			if (this.roles.contains(role)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public void store(HttpServletRequest request) {
		this.remoteAddr = request.getRemoteAddr();
		this.remoteHost = request.getRemoteHost();
		this.remotePort = request.getRemotePort();
		HttpSession session = request.getSession();
		session.setAttribute("remoteClient", this);
	}

	public static RemoteClient load(HttpSession session) {
		return (RemoteClient) session.getAttribute("remoteClient");
	}

}
