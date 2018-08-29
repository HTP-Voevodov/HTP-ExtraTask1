package by.htp.extratask1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.extratask1.bean.News;
import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask1.service.EntryService;
import by.htp.extratask1.service.ServiceException;
import by.htp.extratask1.service.ServiceFactory;

public class NewsFromPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewsFromPage() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set news params from page to newsCriteria
		Criteria<NewsCriteria> newsCriteria = new Criteria<NewsCriteria>(NewsCriteria.class);
		newsCriteria.setCategory(request.getParameter("category"));
		newsCriteria.setSubCategory(request.getParameter("subCategory"));
		//set dynamic params
		for(Object obj : newsCriteria.getGroupSearchName().getEnumConstants()) {
			newsCriteria.add(obj.toString(), request.getParameter(obj.toString()));
		}
		//get factory and entry service
		ServiceFactory factory = ServiceFactory.getInstance();
		EntryService newsService = factory.getEntryService();
		
		try {
			newsService.createEntry(newsCriteria);
		} catch (ServiceException e) {
			//send error info to page
			request.setAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher("add.jsp").forward(request, response);
	}

}
