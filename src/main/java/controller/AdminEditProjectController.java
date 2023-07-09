package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Project;
import model.dao.InfDAO;
import model.dao.UserDAO;
import util.AuthUtil;
import util.FileUtil;

@MultipartConfig
public class AdminEditProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    InfDAO infDAO;
    UserDAO userDAO;
	
    public AdminEditProjectController() {
        super();
        infDAO = new InfDAO();
        userDAO  = new UserDAO();
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
			response.sendRedirect(request.getContextPath() +"/admin/projects?err=2");
			return;
		}
		Project project = infDAO.getItem(id);
		System.out.println(project);
		if(project != null) {
			request.setAttribute("project", project);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/project/edit.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/projects?err=1");
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
			response.sendRedirect(request.getContextPath() +"/admin/projects?err=2");
			return;
		}
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String link = request.getParameter("link");
		String username = "admin";
		int user_id = userDAO.getId(username);
		String picture = null;
		Part filePart = request.getPart("picture");
		
		Project project = infDAO.getItem(id);
		
		if(project == null) {
			response.sendRedirect(request.getContextPath() +"/admin/projects?err=4");
			return;
		}
		String fileName = FileUtil.getName(filePart);
		if(fileName.isEmpty()) {
			picture = project.getPicture();
		}else {
			picture = FileUtil.rename(fileName);
		}
		
		final String dirPathName = request.getServletContext().getRealPath("/upload");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();	
		}
		String filePathName = dirPathName + File.separator + picture;
		
		Project item = new Project(id, name, preview, link, picture, user_id);
		if(infDAO.editItem(item) >0) {
			if(!fileName.isEmpty()) {
				String oldPathName =  dirPathName + File.separator + project.getPicture();
				File oldFile = new File(oldPathName);
				if(oldFile.exists()) {
					oldFile.delete();
				}
				filePart.write(filePathName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/projects?msg=2");
			return;
		}else {
			request.setAttribute("project", project);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/project/edit.jsp?err=1");
			rd.forward(request, response);
			return;

		}
	}

}
