package by.htp.extratask1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.extratask1.controller.command.Command;
import by.htp.extratask1.controller.command.CommandDirector;
import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask1.service.EntryService;
import by.htp.extratask1.service.ServiceException;
import by.htp.extratask1.service.ServiceFactory;

public class EntryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CommandDirector commandDirector = new CommandDirector();
	
    public EntryController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName, responsePage;
		Command command;
		
		commandName = request.getParameter("command");
		command = commandDirector.getCommand(commandName);
	
		responsePage = command.execute(request, response);

		request.getRequestDispatcher(responsePage).forward(request, response);		
	}

}
