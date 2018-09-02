package by.htp.extratask1.domain;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.domain.impl.XMLNews;
import by.htp.extratask1.domain.impl.XMLNewsCategory;
import by.htp.extratask1.domain.impl.XMLNewsRepository;
import by.htp.extratask1.domain.impl.XMLNewsSubCategory;


public class XMLElementsCreator {
	private XMLElementsCreator() {}

	//it helps us to create different repositories with different criteria data types
	public static <E> XMLRepository createRepository(Criteria<E> inputEntry) {
		XMLRepository  repository;
		String entryName = inputEntry.getGroupSearchName().getSimpleName();
	
		switch (entryName) {
		case "NewsCriteria":
			repository = new XMLNewsRepository();
			break;
		default:
			repository = new XMLNewsRepository();
			break;
		}		
		return repository;
	}
	
	//it helps us to create different Categories with different criteria data types
		public static <E> XMLCategory createCategory(Criteria<E> inputEntry) {
			XMLCategory  category;
			String entryName = inputEntry.getGroupSearchName().getSimpleName();
		
			switch (entryName) {
			case "NewsCriteria":
				category = new XMLNewsCategory();
				break;
			default:
				category = new XMLNewsCategory();
				break;
			}		
			return category;
		}
	
	//it helps us to create different SubCategories with different criteria data types
	public static <E> XMLSubCategory createSubCategory(Criteria<E> inputEntry) {
		XMLSubCategory  subCategory;
		String entryName = inputEntry.getGroupSearchName().getSimpleName();
	
		switch (entryName) {
		case "NewsCriteria":
			subCategory = new XMLNewsSubCategory();
			break;
		default:
			subCategory = new XMLNewsSubCategory();
			break;
		}		
		return subCategory;
	}
	//it helps us to create different Entries with different criteria data types
	public static <E> XMLEntry createEntry(Criteria<E> inputCriteria) {
		XMLEntry  news;
		String newsName = inputCriteria.getGroupSearchName().getSimpleName();
	
		switch (newsName) {
		case "NewsCriteria":
			news = new XMLNews();
			break;
		default:
			news = new XMLNews();
			break;
		}		
		return news;
	}
	
}
