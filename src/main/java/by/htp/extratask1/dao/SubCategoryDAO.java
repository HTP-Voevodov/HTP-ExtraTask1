package by.htp.extratask1.dao;

import java.util.List;

import by.htp.extratask1.criteria.Criteria;
//abstract class instead interface because of marshaling problems 
public abstract class SubCategoryDAO {
	public abstract List<?> getAllEntries();
	public abstract EntryDAO searchEntry(String newsName);
	public abstract void setName(String name);
	public abstract String getName();
	public abstract void addEntry(final EntryDAO news);
	public abstract <E> void processSubCategory(CategoryDAO category, Criteria<E> EntryForSearch, List<Criteria<E>> entriesForPage);
	
}
