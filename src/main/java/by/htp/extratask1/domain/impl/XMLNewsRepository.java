package by.htp.extratask1.domain.impl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import by.htp.extratask1.domain.XMLCategory;
import by.htp.extratask1.domain.XMLRepository;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"category"}, name = "repository")
@XmlRootElement(name = "repository")
public class XMLNewsRepository extends XMLRepository{
	
	@XmlElement(name = "category")
	private List<XMLNewsCategory> category = new ArrayList<XMLNewsCategory>();

	public XMLNewsRepository() {}
		
	public List<XMLCategory> getCategories() {
		List<XMLCategory> mapCategory = new ArrayList<XMLCategory>();
		for(XMLNewsCategory cat : category) {
			mapCategory.add(cat);
		}
		return mapCategory;
	
	}
	
	public XMLCategory searchCategory(String categoryName) {
		for(XMLNewsCategory targetCategory: this.category) {
			if (categoryName.equals(targetCategory.getName())){
				return targetCategory;
			}
		}
		return null;
	}
	
	public void addCategory(XMLCategory category) {
		this.category.add((XMLNewsCategory)category);
	}
}
