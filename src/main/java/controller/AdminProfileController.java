package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Infomation;
import model.dao.InfDAO;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfDAO infDAO;
       
    public AdminProfileController() {
        super();
        infDAO = new InfDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/login");
			return;
		}
		String username = "admin";
		Infomation infs = infDAO.getItem(username);
		request.setAttribute("infs", infs);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/profile/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
