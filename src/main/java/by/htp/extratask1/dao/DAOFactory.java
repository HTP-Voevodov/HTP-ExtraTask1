package by.htp.extratask1.dao;

import by.htp.extratask1.dao.impl.XMLRepositoryDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	//TODO add dependencies of type from parameter (XML, JDBC,..)
	
	private final RepositoryDAO repDAO = new XMLRepositoryDAO();

	private DAOFactory() {
	}
	
	public RepositoryDAO getRepositoryDAO() {
		return repDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}

}
