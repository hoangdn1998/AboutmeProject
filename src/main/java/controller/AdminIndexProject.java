package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Project;
import model.bean.User;
import model.dao.InfDAO;
import util.AuthUtil;
import util.DefineUtil;

public class AdminIndexProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InfDAO infDAO;
       
    public AdminIndexProject() {
        super();
        infDAO = new InfDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/login");
			return;
		}
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if(!"admin".equals(userLogin.getUsername())) {
			response.sendRedirect(request.getContextPath() + "/admin/index?err=1");
			return;
		}else {
			int numberOfItems = infDAO.getNumberOfProjectItems();
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
			
			int user_id = userLogin.getUser_id();
			ArrayList<Project> projects = infDAO.getItemsPagination(offset,user_id);
			request.setAttribute("projects", projects);
			request.setAttribute("numberOfItems", numberOfItems);
			request.setAttribute("numberOfPages", numberOfPages);
			request.setAttribute("currentPage", currentPage);
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/project/index.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
