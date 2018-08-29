package by.htp.extratask1.dao;

import java.util.List;

public interface RepositoryDAO {
	public List<?> getCategories();
	public CategoryDAO searchCategory(String categoryName);
	public void addCategory(CategoryDAO category);
}
