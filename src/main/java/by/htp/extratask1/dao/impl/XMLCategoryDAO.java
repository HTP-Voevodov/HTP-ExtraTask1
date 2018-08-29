package by.htp.extratask1.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.dao.CategoryDAO;
import by.htp.extratask1.dao.SubCategoryDAO;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"name", "subCategory"}, name = "category")
public class XMLCategoryDAO implements CategoryDAO{
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "subCategory") 
	private List<XMLNewsSubCategoryDAO> subCategory = new ArrayList<XMLNewsSubCategoryDAO>();
	
	public XMLCategoryDAO() {}
	
	public String getName() {
		return name;
	}
	
	public List<XMLNewsSubCategoryDAO> getSubCategories() {
		return subCategory;
	}
	//search SubCategories for adding new element in structure
	public XMLNewsSubCategoryDAO searchSubCategory(String subCategoryName) {
		for(XMLNewsSubCategoryDAO targetSubCategory: this.subCategory) {
			if (subCategoryName.equals(targetSubCategory.getName())){
				return targetSubCategory;
			}
		}
		return null;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void addSubCategory(SubCategoryDAO subCategory) {
		this.subCategory.add((XMLNewsSubCategoryDAO)subCategory);
	}
	//for search page
	//check subCategory name and run checking of entry fields 
	public <E> void processCategory(Criteria<E> EntryForSearch, List<Criteria<E>> newsForPage) {
		for (XMLNewsSubCategoryDAO subCategory : subCategory) {
			//get subCategories by parameter or all subCetegories
			if(	"".equals(EntryForSearch.getSubCategory()) || EntryForSearch.getSubCategory().equals(subCategory.getName())) {
				subCategory.processSubCategory(this, EntryForSearch, newsForPage);
			}
		}
	}
	
}
