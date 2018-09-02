package by.htp.extratask1.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.dao.DAOException;
import by.htp.extratask1.dao.RepositoryDAO;
import by.htp.extratask1.domain.TransferDataStrucure;
import by.htp.extratask1.domain.XMLCategory;
import by.htp.extratask1.domain.XMLElementsCreator;
import by.htp.extratask1.domain.XMLEntry;
import by.htp.extratask1.domain.XMLRepository;
import by.htp.extratask1.domain.XMLSubCategory;


public class XMLRepositoryDAO implements RepositoryDAO{
	private final String ENTRIES_STORE_PATH = "e:\\entries.xml";
	private JAXBContext context;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	private File entriesStoreFile;
	private XMLRepository repository;
	
	public XMLRepositoryDAO() {
		entriesStoreFile = new File(ENTRIES_STORE_PATH);
	}
	
	
	public <E> void createEntry(Criteria<E> inputCriteria) throws DAOException{
		repository = XMLElementsCreator.createRepository(inputCriteria);
		XMLCategory category;
		XMLSubCategory subCategory;
		XMLEntry entry = XMLElementsCreator.createEntry(inputCriteria);
		
		try {
			//parse XML, read data from file, create classes structure
			initXMLParser(repository.getClass());
			
			readDataFromFile();
			
			category = getCategory(inputCriteria);
			subCategory = getSubCategory(inputCriteria, category);
		
			//add params to news(entry)
			entry.setParams(inputCriteria);

			//add info to subCategory
			subCategory.addEntry(entry);
			
			writeDataToFile();
			
			
		} catch (JAXBException e) {
			throw new DAOException("XML creation Marshaller/Unmarshaller problems (JAXB)", e);
		} catch (FileNotFoundException e) {
			throw new DAOException("XML creation. Source file is not found", e);
		}
	}

	public <E> List<TransferDataStrucure<E>> searchEntries(Criteria<E> inputCriteria) throws DAOException{
		repository = XMLElementsCreator.createRepository(inputCriteria);
		List<TransferDataStrucure<E>> strucutureForPage = new ArrayList<>();
		
		try {
			//parse XML, read data from file, create classes structure
			initXMLParser(repository.getClass());
			
			if (!readDataFromFile()) {
				return null;
			}
			
			searchEntries(inputCriteria, strucutureForPage);
			
		} catch (JAXBException e) {
			throw new DAOException("XML searching. Marshaller/Unmarshaller problems (JAXB)", e);
		}
		return strucutureForPage;
	}
	
	private void initXMLParser(final Class rootClass) throws JAXBException {
		context = JAXBContext.newInstance(rootClass);
		unmarshaller = context.createUnmarshaller();
		marshaller = context.createMarshaller();
	}
	
	private boolean readDataFromFile() throws JAXBException {
		if(entriesStoreFile.exists()) {	
			repository = (XMLRepository)unmarshaller.unmarshal(entriesStoreFile);
			return true;
		} else {
			return false;
		}
		
	}
	
	private void writeDataToFile() throws JAXBException, FileNotFoundException {
		
		//write data to file (serialization)
		marshaller.marshal(repository, new FileOutputStream(ENTRIES_STORE_PATH));
		
		//to console
		marshaller.marshal(repository, System.out);
		System.out.println();
		System.out.println("XML file was created");
	}	
		
	private <E> XMLCategory getCategory(Criteria<E> inputCriteria){
		//search exist category or crate new category
		XMLCategory category;
		
		if(repository.searchCategory(inputCriteria.getCategory()) == null) {
			
			category = XMLElementsCreator.createCategory(inputCriteria);
	 		category.setName(inputCriteria.getCategory());
	 		repository.addCategory((XMLCategory)category);
	 		
	 	} else {
	 		
	 		category = repository.searchCategory(inputCriteria.getCategory());
	 	
	 	}
		
		return category;
	}
	
	private <E> XMLSubCategory getSubCategory(Criteria<E> inputCriteria, XMLCategory category){
		//search exist subCategory or crate new subCategory
		XMLSubCategory subCategory;
	
		if(category.searchSubCategory(inputCriteria.getSubCategory()) == null) {
			
			subCategory = XMLElementsCreator.createSubCategory(inputCriteria);
			subCategory.setName(inputCriteria.getSubCategory());
			category.addSubCategory(subCategory);
			 
		} else {
			
			subCategory = category.searchSubCategory(inputCriteria.getSubCategory());
		
		}
		
		return subCategory;
	}

	private <E> void searchEntries(Criteria<E> inputCriteria, List<TransferDataStrucure<E>> strucutureForPage){
		
		//get categories by param or all cetegories
		for (XMLCategory category : repository.getCategories()) {	
			if(	"".equals(inputCriteria.getCategory()) || inputCriteria.getCategory().equals(category.getName())) {
				category.processCategory(inputCriteria, strucutureForPage);
			}
		}
	}
}
