package by.htp.extratask1.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.dao.CategoryDAO;
import by.htp.extratask1.dao.EntryDAO;
import by.htp.extratask1.dao.SubCategoryDAO;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"name", "news"}, name = "subCcategory")
public class XMLNewsSubCategoryDAO extends SubCategoryDAO{ 
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "news") 
	private List<XMLNewsDAO> news = new ArrayList<XMLNewsDAO>();
	
	public XMLNewsSubCategoryDAO() {}
	
	public String getName() {
		return name;
	}
	
	@Override
	public List<XMLNewsDAO> getAllEntries() {
		return news;
	}
	
	public XMLNewsDAO searchEntry(String newsName) {
		for(XMLNewsDAO targetNews: this.news) {
			if (newsName.equals(targetNews.getName())){
				return targetNews;
			}
		}
		return null;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void addEntry(final EntryDAO entry) {
		this.news.add((XMLNewsDAO)entry);
	}
	//for search page
	//check entry fields and add new value to List for page
	public <E> void processSubCategory(CategoryDAO category, Criteria<E> EntryForSearch, List<Criteria<E>> entriesForPage) {
		for(XMLNewsDAO entryFromRepository: news) {
			//get news by parameter or all news
			// and add info to newsForPage
			if(entryFromRepository.processEntry(EntryForSearch)) {
				//if everyone is equal or if every field is clean
				Criteria<E> criteriaForOutput = new Criteria<E>(EntryForSearch.getGroupSearchName());
				entryFromRepository.createCriteriaForOutput(category, this, criteriaForOutput);
				//create new entry and check uniqueness
				if (!entriesForPage.contains(criteriaForOutput)) {
					entriesForPage.add(criteriaForOutput);
				}
			}		
		}
	}	
}
