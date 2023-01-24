package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.simplesecurity.RemoteClient;

import com.jsf.dao.GradeDAO;
import com.jsf.entities.User;

@Named
@RequestScoped
public class Grade {
//wyświetlanie ocen
private static final String PAGE_GRADE = "/pages/other/grade?faces-redirect=true";
	
	private List<SelectItem> gradeList;
	private String selectedItem;
	private String gradeLabel;
	private String gradeValue;

	@Inject
	GradeDAO gradeDAO;
	
	
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
	
	//generowanie listy
	public ListDataModel<Object[]> getList(String className){
		//zrobić, skomplikowane
		System.out.println(className);
		List<String> userListNames = gradeDAO.getSubjectsByClassName(className);
		
		//for(int i=0;i<userListNames.size();i++) {
		//String subjectName = userListNames[i];
		//List<String> userListSurnames = gradeDAO.getGradesBySubject(subjectName);
		//};
		
		
		List<Object[]> data = new ArrayList<>();
		for(int i=0;i<userListNames.size();i++) {
		 //   data.add(new Object[] {userListNames.get(i),userListSurnames.get(i)});
		}
		//System.out.println(dataTable);
		ListDataModel<Object[]> userDataModel = new ListDataModel<Object[]>(data);
		
		return userDataModel;
	}
	
	//zmienić, to bie ma być lista tylko tabela
	public void generateList(String className){
		gradeDAO.getSubjectsByClassName(className);
		//gradeList = new ArrayList<SelectItem>();
		//for() {
		//gradeList.add(new SelectItem("pobierz wszystkie przedmioty na które uczęszcza uczeń z bazy danych", "Label 1"));
		//}
		System.out.println(className);
		
	}
	
	public String showGradePage(){
		return PAGE_GRADE;
	}
//obliczanie średniej
	
//dodawanie ocen
	
//usuwanie ocen
	
//edytowanie ocen
	
}
