package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;
import util.FileUtil;
import util.StringUtil;

@MultipartConfig
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;   
	 
    public AdminEditUserController() {
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
			response.sendRedirect(request.getContextPath() +"/admin/users?err=2");
			return;
		}
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if("admin".equals(userDAO.getItem(userLogin.getUser_id()).getUsername()) || id == userLogin.getUser_id()) {
			User user = userDAO.getItem(id);
			
			if(user != null) {
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/admin/users?err=1");
				return;
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=3");
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
			response.sendRedirect(request.getContextPath() +"/admin/users?err=2");
			return;
		}
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if("admin".equals(userDAO.getItem(userLogin.getUser_id()).getUsername()) || id == userLogin.getUser_id()) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			String picture = null;
			Part filePart = request.getPart("picture");
			
			User user = userDAO.getItem(id);
			
			if(user == null) {
				response.sendRedirect(request.getContextPath() +"/admin/users?err=4");
				return;
			}
			if(password.isEmpty()) {
				password = user.getPassword();
			}else {
				password = StringUtil.md5(password);
			}
			
			user.setPassword(password);
			user.setFullname(fullname);
			
			
			
			String fileName = FileUtil.getName(filePart);
			if(fileName.isEmpty()) {
				picture = user.getPicture();
			}else {
				picture = FileUtil.rename(fileName);
			}
			
			final String dirPathName = request.getServletContext().getRealPath("/upload");
			File dirFile = new File(dirPathName);
			if(!dirFile.exists()) {
				dirFile.mkdirs();	
			}
			String filePathName = dirPathName + File.separator + picture;
			
			User item = new User(id, username, password, fullname, picture);
			if(userDAO.editItem(item) >0) {
				if(!fileName.isEmpty()) {
					String oldPathName =  dirPathName + File.separator + user.getPicture();
					File oldFile = new File(oldPathName);
					if(oldFile.exists()) {
						oldFile.delete();
					}
					filePart.write(filePathName);
				}
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=2");
				return;
			}else {
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
				rd.forward(request, response);
				return;
	
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=3");
			return;
		}
		
	}

}
