package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Infomation;
import model.bean.News;
import model.bean.Project;
import model.bean.Skill;
import model.bean.Target;
import model.dao.InfDAO;
import model.dao.NewsDAO;
import util.DefineUtil;

public class IndexPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InfDAO infDAO;
    private NewsDAO newsDAO;
    public IndexPublicController() {
        super();
        infDAO = new InfDAO();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username ="admin";
		Infomation inf = infDAO.getItem(username);
		int user_id = inf.getUser().getUser_id();
		
		ArrayList<Skill> skills = infDAO.getSkill(user_id);
		ArrayList<Target> targets = infDAO.getTarget(user_id);
		ArrayList<Project> projects = infDAO.getProject(user_id);
		
		int numberOfItems = newsDAO.getNumberOfItems();
		int numberOfPages = (int) Math.ceil((float)numberOfItems/DefineUtil.NUMBER_PER_PAGE) ; 
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		if(currentPage <1) {
			currentPage = 1;
		}
		if(currentPage > numberOfPages ) {
			currentPage = currentPage -1;
		}
		int offset = (currentPage-1) * DefineUtil.NUMBER_PER_PAGE;
		ArrayList<News> news = newsDAO.getItemsPagination(offset);
		
		request.setAttribute("inf", inf);
		request.setAttribute("skills", skills);
		request.setAttribute("targets", targets);
		request.setAttribute("projects", projects);
		request.setAttribute("news", news);
		request.setAttribute("numberOfItems", numberOfItems);
		request.setAttribute("numberOfPages", numberOfPages);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
