package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;
import util.AuthUtil;
import util.FileUtil;

@MultipartConfig
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsDAO newsDAO;
	CategoryDAO categoryDAO;
       
    public AdminEditNewsController() {
        super();
        newsDAO = new NewsDAO();
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
			response.sendRedirect(request.getContextPath() + "/admin/news?err=2");
			return;
		}
		
		ArrayList<Category> categories = categoryDAO.getItems();
		News objnew = newsDAO.getItem(id);
		request.setAttribute("categories", categories);
		request.setAttribute("objnew", objnew);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/login");
			return;
		}
		
		int cat_id = 0;
		int id = 0;
		try {
			cat_id = Integer.parseInt(request.getParameter("category"));
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?err=2");
		}
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		String picture ="";
		
		Part filePart = request.getPart("picture");
		final String dirPathName = request.getServletContext().getRealPath("/upload");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();	
		}
		
		News objnew = newsDAO.getItem(id);
		if(objnew == null) {
			response.sendRedirect(request.getContextPath() + "/admin/news?err=1");
			return;
		}
		
		String fileName = FileUtil.getName(filePart);
		
		if(fileName.isEmpty()) {
			picture = objnew.getPicture();
		}else {
			picture = FileUtil.rename(fileName);
		}
		
		String filePathName = dirPathName + File.separator + picture;
		Category category = new Category(cat_id, null);
		News item = new News(id, name, preview, detail, picture, null, 0, category);
		if(newsDAO.editItem(item) >0) {
			if(!fileName.isEmpty()) {
				String oldPathName =  dirPathName + File.separator + objnew.getPicture();
					File oldFile = new File(oldPathName);
					if(oldFile.exists()) {
						oldFile.delete();
					}
					filePart.write(filePathName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
			return;
		}else {
			ArrayList<Category> categories = categoryDAO.getItems();
			request.setAttribute("categories", categories);
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
