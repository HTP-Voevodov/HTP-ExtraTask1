package by.htp.extratask1.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.dao.DAOException;
import by.htp.extratask1.dao.DAOFactory;
import by.htp.extratask1.dao.RepositoryDAO;
import by.htp.extratask1.domain.TransferDataStrucure;
import by.htp.extratask1.service.EntryService;
import by.htp.extratask1.service.ServiceException;


public class EntryServiceXMLImpl implements EntryService{
	@Override
	public <E> void createEntry(Criteria<E> inputCriteria) throws ServiceException{
				
		DAOFactory factory = DAOFactory.getInstance();
		RepositoryDAO repository = factory.getRepositoryDAO();
		
		try {
			repository.createEntry(inputCriteria);
		} catch (DAOException e) {
			throw new ServiceException("RepositoryDAO entry creation exeption ", e);
		}
	}
	
	@Override
	public <E> List<TransferDataStrucure<E>> searchEntry(Criteria<E> inputCriteria) throws ServiceException {
		
		List<TransferDataStrucure<E>> entriesForPage = new ArrayList<>();
		DAOFactory factory = DAOFactory.getInstance();
		RepositoryDAO repository = factory.getRepositoryDAO();
		
		try {
			entriesForPage = repository.searchEntries(inputCriteria);
		} catch (DAOException e) {
			throw new ServiceException("RepositoryDAO searching exeption ", e);
		}
		
		return entriesForPage;
	}
	
}
