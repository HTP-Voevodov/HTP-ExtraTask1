package by.htp.extratask1.service;

import by.htp.extratask1.service.impl.EntryServiceXMLImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final EntryService entryService = new EntryServiceXMLImpl();
		
	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		return instance;
	}

	public EntryService getEntryService() {
		return entryService;
	}
}
