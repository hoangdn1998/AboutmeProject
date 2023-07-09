package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NewsDAO;
import util.AuthUtil;

public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsDAO  newsDAO;
    public AdminDelNewsController() {
        super();
       newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/login");
			return;
		}
		int id =0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() +"/admin/news?err=1");
			return;
		}
		
		if(newsDAO.delItem(id) >0) {
			response.sendRedirect(request.getContextPath() +"/admin/news?msg=3");
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/news?err=3");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
