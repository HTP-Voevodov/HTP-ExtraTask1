package by.htp.extratask1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import by.htp.extratask1.dao.CategoryDAO;
import by.htp.extratask1.dao.RepositoryDAO;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"category"}, name = "repository")
@XmlRootElement(name = "repository")
public class XMLRepositoryDAO implements RepositoryDAO{
	
	@XmlElement(name = "category")
	private List<XMLCategoryDAO> category = new ArrayList<XMLCategoryDAO>();

	public List<XMLCategoryDAO> getCategories() {
		return category;
	}
	
	public XMLCategoryDAO searchCategory(String categoryName) {
		for(XMLCategoryDAO targetCategory: this.category) {
			if (categoryName.equals(targetCategory.getName())){
				return targetCategory;
			}
		}
		return null;
	}
	
	public void addCategory(CategoryDAO category) {
		this.category.add((XMLCategoryDAO)category);
	}
}
