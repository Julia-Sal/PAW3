package code;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

public class LazyUserDataModel extends LazyDataModel<User>{
	private static final long serialVersionUID = 1L;
	private List<User> datasource;
	
	@Inject 
	UserDAO userDAO;
	
	
	public LazyUserDataModel(List<User> datasource) {
        this.datasource = datasource;
    }
	
	@Override
	public List<User> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		
				List<User> users = datasource.stream()
						.skip(offset)
						.limit(pageSize)
						.collect(Collectors.toList());
				
		return users;
	    }
	
	 @Override
	    public User getRowData(String rowKey) {
	        for (User user : datasource) {
	            if (user.getIdUser() == Integer.parseInt(rowKey)) {
	                return user;
	            }
	        }

	        return null;
	    }

	    @Override
	    public String getRowKey(User user) {
	        return String.valueOf(user.getIdUser());
	    }
}
