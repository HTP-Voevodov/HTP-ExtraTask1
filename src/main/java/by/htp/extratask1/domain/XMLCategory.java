package by.htp.extratask1.domain;

import java.util.List;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.domain.impl.XMLNewsSubCategory;

public interface XMLCategory {
	public String getName();
	public void setName(String name);
	public XMLSubCategory searchSubCategory(String subCategoryName);
	public void addSubCategory(XMLSubCategory subCategory);
	public <E> void processCategory(Criteria<E> entryForSearch, List<TransferDataStrucure<E>> entryForPage);
}
