package by.htp.extratask1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.extratask1.bean.News;
import by.htp.extratask1.criteria.Criteria;
import by.htp.extratask1.criteria.SearchCriteria;
import by.htp.extratask1.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask1.service.EntryService;
import by.htp.extratask1.service.ServiceException;
import by.htp.extratask1.service.ServiceFactory;


public class NewsFromStore extends HttpServlet {
	private static final long serialVersionUID = 11112222223333L;
       
    public NewsFromStore() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set news params from page to newsCriteria
		Criteria<NewsCriteria> newsCriteria = new Criteria<NewsCriteria>(NewsCriteria.class);
		newsCriteria.setCategory(request.getParameter("category"));
		newsCriteria.setSubCategory(request.getParameter("subCategory"));
		//set dynamic params
		for(Object obj : newsCriteria.getGroupSearchName().getEnumConstants()) {
			newsCriteria.add(obj.toString(), request.getParameter(obj.toString()));
		}
		//get factory and news service
		ServiceFactory factory = ServiceFactory.getInstance();
		EntryService newsService = factory.getEntryService();
		
		List<Criteria<NewsCriteria>> newsForPage = new ArrayList<>();
		
		try {
			//get news by params
			newsForPage = newsService.searchEntry(newsCriteria);
		} catch (ServiceException e) {
			//send error info to page
			request.setAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		}
	
		if(newsForPage != null) {
			//send news info to page, if it's exists
			request.setAttribute("newsForPage", mappingFromCriteria(newsForPage));
		}
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//Mapping data for jsp view
	//from Criteria to News-bean
	private List<News> mappingFromCriteria(List<Criteria<NewsCriteria>> newsForPage) {
		List<News> news = new ArrayList<News>();
		for(Criteria<NewsCriteria> criteria : newsForPage) {
			News pieceOfNews = new News();
			pieceOfNews.setCategory(criteria.getCategory());
			pieceOfNews.setSubCategory(criteria.getSubCategory());
			pieceOfNews.setNewsName(criteria.getDynamicCriterias().get("newsName"));
			pieceOfNews.setProvider(criteria.getDynamicCriterias().get("provider"));
			pieceOfNews.setDateOfIssue(criteria.getDynamicCriterias().get("dateOfIssue"));
			pieceOfNews.setNewsBody(criteria.getDynamicCriterias().get("newsBody"));
			news.add(pieceOfNews);
		}
		return news;
	}
}
