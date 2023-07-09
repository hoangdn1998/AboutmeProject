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

import model.bean.Project;
import model.bean.User;
import model.dao.InfDAO;
import model.dao.UserDAO;
import util.AuthUtil;
import util.FileUtil;

@MultipartConfig
public class AdminAddProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InfDAO infDAO;
	UserDAO userDAO;
       
    public AdminAddProjectController() {
        super();
        infDAO = new InfDAO();
        userDAO  = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/admin/index");
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if(!"admin".equals(userLogin.getUsername())) {
			response.sendRedirect(request.getContextPath() + "/admin/projects?err=4");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/project/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/admin/index");
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if(!"admin".equals(userLogin.getUsername())) {
			response.sendRedirect(request.getContextPath() + "/admin/projects?err=4");
			return;
		}
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String link = request.getParameter("link");
		String username = "admin";
		int user_id = userDAO.getId(username);
		Part filePart = request.getPart("picture");
		
		final String dirPathName = request.getServletContext().getRealPath("/upload");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();	
		}
		String fileName = FileUtil.getName(filePart);
		String picture = FileUtil.rename(fileName);
		String filePathName = dirPathName + File.separator + picture;
		
		
		Project item = new Project(0, name, preview, link, picture, user_id);
		
		if(infDAO.addItem(item) >0) {
			if(!fileName.isEmpty()) {
				filePart.write(filePathName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/projects?msg=1");
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/projects/add?err=1");
			return;
		}
	}

}
