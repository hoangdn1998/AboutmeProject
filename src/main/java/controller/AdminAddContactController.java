package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDAO;

public class AdminAddContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactDAO contactDAO;
       
    public AdminAddContactController() {
        super();
        contactDAO = new ContactDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String website = request.getParameter("address");
		String message = request.getParameter("message");
		
		Contact item = new Contact(0, name, phone, email, website, message);
		if(contactDAO.addItem(item) > 0) {
			response.sendRedirect(request.getContextPath() +"/public/thanks.jsp");
			return;
		}else {
			response.sendRedirect(request.getContextPath() +"/public/404.jsp");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
