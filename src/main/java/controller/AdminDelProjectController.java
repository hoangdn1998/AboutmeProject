package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.InfDAO;
import util.AuthUtil;

public class AdminDelProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InfDAO infDAO;
	
    public AdminDelProjectController() {
        super();
        infDAO = new InfDAO();
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
			response.sendRedirect(request.getContextPath() +"/admin/projects?err=1");
			return;
		}

		if(infDAO.delItem(id) >0) {
			response.sendRedirect(request.getContextPath() +"/admin/projects?msg=3");
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/projects?err=3");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
