package by.htp.extratask1.service;

import java.util.List;

import by.htp.extratask1.domain.TransferDataStrucure;
import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.criteria.SearchCriteria.NewsCriteria;

public interface EntryService {
	public <E> void createEntry(Criteria<E> inputEntry) throws ServiceException;
	public <E> List<TransferDataStrucure<E>> searchEntry(Criteria<E> entryForSearch) throws ServiceException;
}
