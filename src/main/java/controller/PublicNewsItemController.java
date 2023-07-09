package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;

public class PublicNewsItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsDAO newsDAO;
	CategoryDAO categoryDAO;
       
    public PublicNewsItemController() {
        super();
        newsDAO = new NewsDAO();
        categoryDAO = new CategoryDAO(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int news_id =0;
		try {
			news_id = Integer.parseInt(request.getParameter("news_id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/public/news.jsp?err=1");
			return;
		}
		HttpSession session = request.getSession();
		News newsItem = newsDAO.getItem(news_id);
		if(newsItem == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		String hasVisited = (String) session.getAttribute("Hasvisted" + news_id);
		if (hasVisited == null) {
			session.setAttribute("Hasvisted" + news_id, "yes");
			session.setMaxInactiveInterval(1200);
			newsDAO.increaseView(news_id);
		}
		newsItem = newsDAO.getItem(news_id);
		if (newsItem == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		int limit =2;
		ArrayList<News> news = newsDAO.getMaxItem(limit);
		ArrayList<Category> categories = categoryDAO.getItems();
		request.setAttribute("news", news);
		request.setAttribute("newsItem", newsItem);
		request.setAttribute("categories", categories);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/news.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
