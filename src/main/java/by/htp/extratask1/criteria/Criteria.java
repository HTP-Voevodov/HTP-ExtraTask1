package by.htp.extratask1.criteria;

import java.util.HashMap;
import java.util.Map;

import by.htp.extratask1.bean.News;

public class Criteria<E> {
	private String category;
	private String subCategory;
	private Class<E> groupSearchName;
	//these params we can change via SearchCriteria
	private Map<String, String> dynamicCriterias = new HashMap<String, String>();
	
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
	public Criteria(Class<E> groupSearchName) {
		this.groupSearchName = groupSearchName;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((subCategory == null) ? 0 : subCategory.hashCode());
		for (String key : dynamicCriterias.keySet()) {
			result = prime * result + ((dynamicCriterias.get(key) == null) ? 0 : dynamicCriterias.get(key).hashCode());
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criteria<E> other = (Criteria<E>) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (subCategory == null) {
			if (other.subCategory != null)
				return false;
		} else if (!subCategory.equals(other.subCategory))
			return false;
		
		for (String key : dynamicCriterias.keySet()) {
			if (dynamicCriterias.get(key) == null) {
				if (other.dynamicCriterias.get(key) != null)
					return false;
			} else if (!dynamicCriterias.get(key).equals(other.dynamicCriterias.get(key)))
				return false;
		}
		return true;
	}
}
