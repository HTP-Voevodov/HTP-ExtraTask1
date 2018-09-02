package by.htp.extratask1.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask1.service.EntryService;
import by.htp.extratask1.service.ServiceException;
import by.htp.extratask1.service.ServiceFactory;

public class CreationEntryCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// set news params from page to newsCriteria
		Criteria<NewsCriteria> newsCriteria = new Criteria<NewsCriteria>(NewsCriteria.class);
		newsCriteria.setCategory(request.getParameter("category"));
		newsCriteria.setSubCategory(request.getParameter("subCategory"));
		// set dynamic params
		for (Object obj : newsCriteria.getGroupSearchName().getEnumConstants()) {
			newsCriteria.add(obj.toString(), request.getParameter(obj.toString()));
		}
		// get factory and entry service
		ServiceFactory factory = ServiceFactory.getInstance();
		EntryService newsService = factory.getEntryService();

		try {
			newsService.createEntry(newsCriteria);
		} catch (ServiceException e) {
			// send error info to page
			request.setAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		}
		return "add.jsp";
	}

}
