package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDAO;
import util.AuthUtil;

public class AdminEditContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactDAO contactDAO;
       
    public AdminEditContactController() {
        super();
        contactDAO = new ContactDAO();
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
			response.sendRedirect(request.getContextPath() +"/admin/contacts?err=1");
			return;
		}
		Contact contact = contactDAO.getItem(id);
		if(contact != null) {
			request.setAttribute("contact", contact);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/contact/edit.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/contacts?err=1");
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
			response.sendRedirect(request.getContextPath() + "/admin/contacts?err=1");
			return;
		}
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String message = request.getParameter("message");
		
		Contact item = new Contact(id, name, phone, email, address, message);
		if(contactDAO.editItem(item)>0) {
			response.sendRedirect(request.getContextPath() + "/admin/contacts?msg=1");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/contact/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}


	}

}
