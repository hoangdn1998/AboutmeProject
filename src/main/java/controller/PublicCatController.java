package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDAO categoryDAO;
	NewsDAO newsDAO;
       
    public PublicCatController() {
        super();
        categoryDAO = new CategoryDAO();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cat_id =0;
		try {
			cat_id = Integer.parseInt(request.getParameter("cat_id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/public/cat.jsp?err=1");
			return;
		}
		
		ArrayList<News> cat_news = newsDAO.getItemByCat(cat_id);
		int limit =2;
		ArrayList<News> news = newsDAO.getMaxItem(limit);
		ArrayList<Category> categories = categoryDAO.getItems();
		Category category = categoryDAO.getItem(cat_id);
		
		request.setAttribute("news", news);
		request.setAttribute("cat_news", cat_news);
		request.setAttribute("category", category);
		request.setAttribute("categories", categories);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/cat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
