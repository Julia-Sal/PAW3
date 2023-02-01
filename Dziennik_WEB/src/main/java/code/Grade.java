package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.context.Flash;


import com.jsf.dao.GradeDAO;
import com.jsf.dao.UserDAO;
import com.jsf.entities.User;
import com.jsf.dao.ClassDAO;


@Named
@RequestScoped
public class Grade {
//wyświetlanie ocen
private static final String PAGE_GRADE = "/pages/other/grade?faces-redirect=true";
private static final String PAGE_ADD_GRADE = "/pages/teacher/addGrade?faces-redirect=true";
	
	
	private String selectedItem;
	private String gradeLabel;
	private int gradeValue;
	private String className;
	private String studentName;
	private ListDataModel<Object[]> userDataModel;
	private int gradeWeight;
	private int studentID;
	
	
	@Inject
	Flash flash;
	
	@Inject
	GradeDAO gradeDAO;
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	ClassDAO classDAO;
	
	
	
	public int getGradeValue() {
		return gradeValue;
	}
	public void setGradeValue(int gradeValue) {
		this.gradeValue = gradeValue;
	}
	
	public String getGradeLabel() {
		return gradeLabel;
	}
	public void setGradeLabel(String gradeLabel) {
		this.gradeLabel = gradeLabel;
	}
	
	public String getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	public ListDataModel<Object[]> getUserDataModel() {
		return userDataModel;
	}
	public void setUserDataModel(ListDataModel<Object[]> userDataModel) {
		this.userDataModel = userDataModel;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public int getGradeWeight() {
		return gradeWeight;
	}
	public void setGradeWeight(int gradeWeight) {
		this.gradeWeight = gradeWeight;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	
	public ListDataModel<Object[]> getList(String className, String login){//generowanie listy z ocenami
		
		
		List<Object[]> data = new ArrayList<>();
		List<String> userSubjectList = gradeDAO.getSubjectsByClassName(className);
		
		for(String subject : userSubjectList) {
			List<Integer> userGradesList = gradeDAO.getGradesBySubject(subject, login);
			List<String> userGradesListString = userGradesList.stream().map(Object::toString).collect(Collectors.toList());
			Double avg = Math.round(gradeDAO.getAVG(subject, login)*100.0)/100.0;
			data.add(new Object[] {subject, String.join(", ", userGradesListString), avg});
			
		}
		ListDataModel<Object[]> userDataModel = new ListDataModel<Object[]>(data);
		
		
		return userDataModel;
		
	}
	
	//wyświetlanie tabeli do edycji ocen
	public ListDataModel<Object[]> getEditGradesList(String login){
		
		className = (String) flash.get("className");
		
		List<Object[]> data = new ArrayList<>();
		String subject = gradeDAO.getSubjectByLogin(login);
		List<User> usersList = userDAO.getUsersByClassName(className);
		
		for(User userName : usersList) {
			List<Integer> userGradesList = gradeDAO.getGradesBySubject(subject, userName.getLogin());
			List<String> userGradesListString = userGradesList.stream().map(Object::toString).collect(Collectors.toList());
			Double avg = Math.round(gradeDAO.getAVG(subject, userName.getLogin())*100.0)/100.0;
			data.add(new Object[] {userName.getName() + " " + userName.getSurname(), userGradesListString , avg});
		}
		ListDataModel<Object[]> userDataModel = new ListDataModel<Object[]>(data);
		flash.put("className", className);
		return userDataModel;
	}
	
	public String showGradePage(){//pokaż listę ocen - uczeń
		return PAGE_GRADE;
	}
	public String showAddGradePage(String className){//pokaż stronę zarządzania ocenami - nauczyciel
		
		flash.put("className", className);
		return PAGE_ADD_GRADE;
	}
	

//usuwanie ocen
	
	public void deleteGrade() {
		System.out.println("delete");
	}

	public List<String> getClassList(String login) {//znajdz klasy, do których jest przypisany nauczany przedmiot
		
		String subjectName = gradeDAO.getSubjectByLogin(login);
		List<String> classList = gradeDAO.getClassBySubject(subjectName);
		flash.put("subjectName", subjectName);
		return classList;
	}
	
	public List<User> getStudentList() {//Wyświetl studentów z danej klasy
		try { 
			className = (String) flash.get("className");
			List<User> userName = userDAO.getUsersByClassName(className);
			flash.put("className", className);
			return userName;
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Coś poszło nie tak."));
			return null;}
	}
	
	public void addGrade() {
		String subjectName = (String) flash.get("subjectName");
		flash.put("className", className);
		try {
			gradeDAO.addGrade(gradeValue, gradeWeight, studentID, subjectName);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Dodano ocenę."));
		}catch(Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Coś poszło nie tak, spróbuj ponownie."));
		}
	}
	
	
}
