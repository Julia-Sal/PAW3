package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.simplesecurity.RemoteClient;

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
	private String gradeValue;
	private String className;
	private String studentName;
	private ListDataModel<Object[]> userDataModel;
	private List<String> classList;
	private List<String> studentList;
	private int gradeWeight;
	
	@Inject
	GradeDAO gradeDAO;
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	ClassDAO classDAO;
	

	public String getGradeValue() {
		return gradeValue;
	}
	public void setGradeValue(String gradeValue) {
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
	
	//generowanie listy
	public ListDataModel<Object[]> getList(String className, String login){
		List<Object[]> data = new ArrayList<>();
		List<String> userSubjectList = gradeDAO.getSubjectsByClassName(className);
		for(String subject : userSubjectList) {
			List<Integer> userGradesList = gradeDAO.getGradesBySubject(subject, login);
			List<String> userGradesListString = userGradesList.stream().map(Object::toString).collect(Collectors.toList());
			data.add(new Object[] {subject, String.join(",", userGradesListString)});
		}
		ListDataModel<Object[]> userDataModel = new ListDataModel<Object[]>(data);
		
		return userDataModel;
	}

	public String showGradePage(){//pokaż listę ocen - uczeń
		return PAGE_GRADE;
	}
	public String showAddGradePage(){//pokaż stronę zarządzania ocenami - nauczyciel
		return PAGE_ADD_GRADE;
	}
	
//obliczanie średniej
	
//dodawanie ocen
	
//usuwanie ocen
	
//edytowanie ocen
	
//wyświetlanie klas z ocenami
	//nauczyciel jest podłączony do przedmiotów
	//znajdz klasy, które są podpięte do przedmiotu
	//przekaż je do przycisków
	//przkaż listę uczniów z tej klasy do wysównej listy
	//przekaż oceny do wysównej listy
	//
	public List<String> getClassList(String login) {//znajdz klasy, do których jest przypisany nauczany przedmiot
		String subjectName = gradeDAO.getSubjectByLogin(login);
		List<String> classList = gradeDAO.getClassBySubject(subjectName);
		return classList;
	}
	public void setClassList(List<String> classList) {
		this.classList = classList;
	}
	
	public List<String> getStudentList(String className) {
		List<User> userListNames = userDAO.getUsersByClassName(className);
		
		
		return null;
	}
	
	public void setStudentList(List<String> studentList) {
		this.studentList = studentList;
	}
	
	
	
}
