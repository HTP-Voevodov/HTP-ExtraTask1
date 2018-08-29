package by.htp.extratask1.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import by.htp.extratask1.bean.News;
import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.criteria.SearchCriteria;
import by.htp.extratask1.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask1.dao.CategoryDAO;
import by.htp.extratask1.dao.DAOFactory;
import by.htp.extratask1.dao.EntryDAO;
import by.htp.extratask1.dao.SubCategoryDAO;
import by.htp.extratask1.dao.impl.EntryElementsCreator;
import by.htp.extratask1.dao.impl.XMLCategoryDAO;
import by.htp.extratask1.dao.impl.XMLNewsDAO;
import by.htp.extratask1.dao.impl.XMLNewsSubCategoryDAO;
import by.htp.extratask1.dao.impl.XMLRepositoryDAO;
import by.htp.extratask1.service.EntryService;
import by.htp.extratask1.service.ServiceException;

public class EntryServiceXMLImpl implements EntryService{
	private final String ENTRIES_STORE_PATH = "e:\\entries.xml";
	
	@Override
	public <E> void createEntry(Criteria<E> inputEntry) throws ServiceException{
		File entriesStoreFile = new File(ENTRIES_STORE_PATH);
		//get factory and repository
		DAOFactory factory = DAOFactory.getInstance();
		XMLRepositoryDAO repository = (XMLRepositoryDAO)factory.getRepositoryDAO();
		
		JAXBContext context;
		Marshaller marshaller;
		
		try {
			//parse XML, read data from file, create classes structure
			context = JAXBContext.newInstance(XMLRepositoryDAO.class);
			if(entriesStoreFile.exists()) {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				repository = (XMLRepositoryDAO)unmarshaller.unmarshal(entriesStoreFile);
			}
			
			marshaller = context.createMarshaller();
			CategoryDAO categoryDAO;
			//entry is news
			EntryDAO entryDAO =	EntryElementsCreator.createEntry(inputEntry);
			SubCategoryDAO subCategoryDAO;
			//search exist category or crate new category
			if(repository.searchCategory(inputEntry.getCategory()) == null) {
		 		categoryDAO = new XMLCategoryDAO() ;
		 		categoryDAO.setName(inputEntry.getCategory());
		 		repository.addCategory((XMLCategoryDAO)categoryDAO);
		 	} else {
		 		categoryDAO = repository.searchCategory(inputEntry.getCategory());
		 	}
			//search exist subCategory or crate new subCategory
			if(categoryDAO.searchSubCategory(inputEntry.getSubCategory()) == null) {
				subCategoryDAO = EntryElementsCreator.createSubCategory(inputEntry);
				subCategoryDAO.setName(inputEntry.getSubCategory());
				categoryDAO.addSubCategory(subCategoryDAO);
			} else {
				subCategoryDAO = categoryDAO.searchSubCategory(inputEntry.getSubCategory());
			}
			//add params to news(entry)
			entryDAO.setParams(inputEntry);

			//add info to subCategory
			subCategoryDAO.addEntry(entryDAO);
			//write data to file (serialization)
			marshaller.marshal(repository, new FileOutputStream(ENTRIES_STORE_PATH));
			marshaller.marshal(repository, System.out);
			System.out.println();
			System.out.println("XML file was been created");
		} catch (JAXBException e) {
			throw new ServiceException("XML Marshaller/Unmarshaller problems (JAXB)", e);
		} catch (FileNotFoundException e) {
			throw new ServiceException("Source file is not found", e);
		}
	}
	
	@Override
	public <E> List<Criteria<E>> searchEntry(Criteria<E> EntryForSearch) throws ServiceException {
		List<Criteria<E>> entriesForPage;
		File file = new File(ENTRIES_STORE_PATH);
		entriesForPage = new ArrayList<>();
		//get factory and repository
		DAOFactory factory = DAOFactory.getInstance();
		XMLRepositoryDAO repository = (XMLRepositoryDAO)factory.getRepositoryDAO();
		JAXBContext context;
		
		try {
			//parse XML, read data from file, create classes structure
			context = JAXBContext.newInstance(XMLRepositoryDAO.class);
			if(file.exists()) {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				repository = (XMLRepositoryDAO)unmarshaller.unmarshal(file);
			} else {
				return null;
			}
			//get categories by parametr or all cetegories
			for (CategoryDAO category : repository.getCategories()) {
				if(	"".equals(EntryForSearch.getCategory()) || EntryForSearch.getCategory().equals(category.getName())) {
					category.processCategory(EntryForSearch, entriesForPage);
				}				
			}
		} catch (JAXBException e) {
			throw new ServiceException("XML Unmarshaller problems (JAXB)", e);
		}
		return entriesForPage;
	}
	
}
