package by.htp.extratask1.dao.impl;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask1.dao.CategoryDAO;
import by.htp.extratask1.dao.EntryDAO;
import by.htp.extratask1.dao.SubCategoryDAO;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"name", "provider", "dateOfIssue", "newsBody"}, name = "news")
public class XMLNewsDAO extends EntryDAO{
	
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "provider")
	private String provider;
	@XmlElement(name = "dateOfIssue")
	private String dateOfIssue;
	@XmlElement(name = "newsBody")
	private String newsBody;
	
	public XMLNewsDAO() {}
	

	
	
	public String getName() {
		return name;
	}
	public String getProvider() {
		return provider;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public String getNewsBody() {
		return newsBody;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public void setNewsBody(String newsBody) {
		this.newsBody = newsBody;
	}

	public <E> void setParams(Criteria<E> inputEntry) {
		this.name = inputEntry.getDynamicCriterias().get("newsName");
		this.provider = inputEntry.getDynamicCriterias().get("provider");
		this.dateOfIssue = inputEntry.getDynamicCriterias().get("dateOfIssue");
		this.newsBody = inputEntry.getDynamicCriterias().get("newsBody");
	}
	//for search page
	//check entry fields for search 
	public <E> boolean processEntry(Criteria<E> EntryForSearch) {
		Map<String, String> dynamicCriterias = EntryForSearch.getDynamicCriterias();
		//check new by parameters
		if(!"".equals(dynamicCriterias.get("newsName")) && !this.name.equals(dynamicCriterias.get("newsName")))
			return false;
		if(!"".equals(dynamicCriterias.get("provider")) && !this.provider.contains(dynamicCriterias.get("provider")))
			return false;
		if(!"".equals(dynamicCriterias.get("dateOfIssue")) && !this.dateOfIssue.equals(dynamicCriterias.get("dateOfIssue")))
			return false;
		if(!"".equals(dynamicCriterias.get("newsBody")) && !this.newsBody.contains(dynamicCriterias.get("newsBody")))
			return false;
			
		return true;
	}
	
	public <T> void createCriteriaForOutput(CategoryDAO category, SubCategoryDAO subCategory, Criteria<T> criteriaForInput){
		criteriaForInput.setCategory(category.getName());
		criteriaForInput.setSubCategory(subCategory.getName());
		criteriaForInput.add("newsName", this.name);
		criteriaForInput.add("provider", this.provider);
		criteriaForInput.add("dateOfIssue", this.dateOfIssue);
		criteriaForInput.add("newsBody", this.newsBody);
	}
}
