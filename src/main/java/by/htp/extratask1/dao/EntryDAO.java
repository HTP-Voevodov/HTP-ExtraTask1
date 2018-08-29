package by.htp.extratask1.dao;

import by.htp.extratask1.criteria.Criteria;
//abstract class instead interface because of marshaling problems 
public abstract class EntryDAO {

	public abstract String getName();
	public abstract <E> void setParams(Criteria<E> inputEntry);
	public abstract <E> boolean processEntry(Criteria<E> EntryForSearch);
	public abstract <E> void createCriteriaForOutput(CategoryDAO category, SubCategoryDAO subCategory, Criteria<E> criteriaForInput);
	
}
