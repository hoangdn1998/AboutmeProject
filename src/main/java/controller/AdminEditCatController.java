package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import util.AuthUtil;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDAO categoryDAO;
       
    public AdminEditCatController() {
        super();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/login");
			return;
		}
		int id =0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() +"/admin/cats?err=1");
			return;
		}
		Category category = categoryDAO.getItem(id);
		if(category != null) {
			request.setAttribute("category", category);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=1");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/login");
			return;
		}
		int id =0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=1");
			return;
		}
		String name = request.getParameter("category");
		Category item = new Category(id, name);
		
		if(categoryDAO.editItem(item) >0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=2");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp?err=1");
			rd.forward(request, response);
			return;

		}
	}

}
