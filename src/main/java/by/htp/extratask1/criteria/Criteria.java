package by.htp.extratask1.criteria;

import java.util.HashMap;
import java.util.Map;


public class Criteria<E> {
	private String category;
	private String subCategory;
	private Class<E> groupSearchName;
	//these params we can change via SearchCriteria
	private Map<String, String> dynamicCriterias = new HashMap<String, String>();
	
	public Criteria(Class<E> groupSearchName) {
		this.groupSearchName = groupSearchName;
	}
	
	public String getCategory() {
		return category;
	}
	public String getSubCategory() {
		return subCategory;
	}	
	public void setCategory(String category) {
		this.category = category;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}	
	
	public Class<E> getGroupSearchName(){
		return groupSearchName;
	}
	//get Dynamic params
	public Map<String, String> getDynamicCriterias(){
		return dynamicCriterias;
	}
	public void add(String parName, String value) {
		dynamicCriterias.put(parName, value);
	}
}
