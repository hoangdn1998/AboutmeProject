package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    public AdminDelUserController() {
        super();
        userDAO = new UserDAO();
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
			response.sendRedirect(request.getContextPath() +"/admin/users?err=1");
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		
		User user = userDAO.getItem(id);
		if("admin".equals(user.getUsername())) {
			response.sendRedirect(request.getContextPath() +"/admin/users?err=5");
			return;
		}else {
			if("admin".equals(userLogin.getUsername())) {
				if(userDAO.delItem(id) >0) {
					response.sendRedirect(request.getContextPath() +"/admin/users?msg=3");
					return;
				}else {
					response.sendRedirect(request.getContextPath() + "/admin/users?err=3");
					return;
				}
			}else {
				response.sendRedirect(request.getContextPath() +"/admin/users?err=3");
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
