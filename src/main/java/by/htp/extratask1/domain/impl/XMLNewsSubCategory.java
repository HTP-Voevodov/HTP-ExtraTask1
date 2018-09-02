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
import by.htp.extratask1.domain.XMLEntry;
import by.htp.extratask1.domain.XMLSubCategory;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"name", "news"}, name = "subCategory")
public class XMLNewsSubCategory implements XMLSubCategory{ 
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "news") 
	private List<XMLNews> news = new ArrayList<XMLNews>();
	
	public XMLNewsSubCategory() {}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//public List<XMLNews> getAllEntries() {
	//	return news;
	//}
	
	public XMLEntry searchEntry(String newsName) {
		for(XMLNews targetNews: this.news) {
			if (newsName.equals(targetNews.getName())){
				return targetNews;
			}
		}
		return null;
	}
	
	public void addEntry(final XMLEntry entry) {
		this.news.add((XMLNews)entry);
	}
	
	
	//for search page
	//check entry fields and add new value to List for page
	public <E> void processSubCategory(XMLCategory category, Criteria<E> criteriaForSearch, List<TransferDataStrucure<E>> strucureForPage) {
		for(XMLNews entryFromRepository: news) {
			//get news by parameter or all news
			// and add info to newsForPage
			if(entryFromRepository.processEntry(criteriaForSearch)) {
				//if everyone is equal or if every field is clean
				TransferDataStrucure<E> tmpStructure = new TransferDataStrucure<E>(criteriaForSearch.getGroupSearchName());
				
				entryFromRepository.createStructureForOutput(category, this, tmpStructure);
				//create new entry and check uniqueness
				if (!strucureForPage.contains(tmpStructure)) {
					strucureForPage.add(tmpStructure);
				}
			}		
		}
	}	
}
