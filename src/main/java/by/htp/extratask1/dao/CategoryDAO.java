package by.htp.extratask1.dao;

import java.util.List;

import by.htp.extratask1.criteria.Criteria;

public interface CategoryDAO {
	public List<?> getSubCategories();
	public SubCategoryDAO searchSubCategory(String categoryName);
	public void setName(String name);
	public String getName();
	public void addSubCategory(SubCategoryDAO subCategory);
	public <E> void processCategory(Criteria<E> EntryForSearch, List<Criteria<E>> newsForPage);
}
