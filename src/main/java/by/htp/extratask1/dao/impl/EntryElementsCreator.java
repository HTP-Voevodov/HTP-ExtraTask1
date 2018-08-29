package by.htp.extratask1.dao.impl;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.dao.EntryDAO;
import by.htp.extratask1.dao.RepositoryDAO;
import by.htp.extratask1.dao.SubCategoryDAO;

public class EntryElementsCreator {
	private EntryElementsCreator() {}
	//it helps us to create different SubCategories with different criteria data types
	public static <E> SubCategoryDAO createSubCategory(Criteria<E> inputEntry) {
		SubCategoryDAO  subCategory;
		String entryName = inputEntry.getGroupSearchName().getSimpleName();
	
		switch (entryName) {
		case "NewsCriteria":
			subCategory = new XMLNewsSubCategoryDAO();
			break;
		default:
			subCategory = new XMLNewsSubCategoryDAO();
			break;
		}		
		return subCategory;
	}
	//it helps us to create different Entries with different criteria data types
	public static <E> EntryDAO createEntry(Criteria<E> inputEntry) {
		EntryDAO  entry;
		String entryName = inputEntry.getGroupSearchName().getSimpleName();
	
		switch (entryName) {
		case "NewsCriteria":
			entry = new XMLNewsDAO();
			break;
		default:
			entry = new XMLNewsDAO();
			break;
		}		
		return entry;
	}
	
}
