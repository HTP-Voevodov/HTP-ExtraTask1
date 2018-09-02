package by.htp.extratask1.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.domain.TransferDataStrucure;
import by.htp.extratask1.domain.XMLCategory;
import by.htp.extratask1.domain.XMLSubCategory;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"name", "subCategory"}, name = "category")
public class XMLNewsCategory implements XMLCategory{
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "subCategory") 
	private List<XMLNewsSubCategory> subCategory = new ArrayList<XMLNewsSubCategory>();
	
	public XMLNewsCategory() {}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<XMLNewsSubCategory> getSubCategories() {
		return subCategory;
	}
	//search SubCategories for adding new element in structure
	public XMLSubCategory searchSubCategory(String subCategoryName) {
		for(XMLNewsSubCategory targetSubCategory: this.subCategory) {
			if (subCategoryName.equals(targetSubCategory.getName())){
				return targetSubCategory;
			}
		}
		return null;
	}
	
	
	public void addSubCategory(XMLSubCategory subCategory) {
		this.subCategory.add((XMLNewsSubCategory)subCategory);
	}
	
	
	//for search page
	//check subCategory name and run checking of entry fields 
	public <E> void processCategory(Criteria<E> entryForSearch, List<TransferDataStrucure<E>> strucureForPage) {
		for (XMLNewsSubCategory subCategory : subCategory) {
			//get subCategories by parameter or all subCetegories
			if(	"".equals(entryForSearch.getSubCategory()) || entryForSearch.getSubCategory().equals(subCategory.getName())) {
				subCategory.processSubCategory(this, entryForSearch, strucureForPage);
			}
		}
	}
	
}
