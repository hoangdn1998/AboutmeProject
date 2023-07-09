package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Part;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.News;
import model.bean.User;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;
import util.AuthUtil;
import util.FileUtil;

@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    NewsDAO newsDAO;
    CategoryDAO categoryDAO;

    public AdminAddNewsController() {
        super();
        newsDAO = new NewsDAO();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() +"/admin/index");
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		
		if(!"admin".equals(userLogin.getUsername())) {
			response.sendRedirect(request.getContextPath() + "/admin/news?err=4");
			return;
		}
		ArrayList<Category> categories = categoryDAO.getItems();
		request.setAttribute("categories", categories);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp");
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
			response.sendRedirect(request.getContextPath() + "/admin/news?err=4");
			return;
		}
		int cat_id =0;
		try {
			cat_id = Integer.parseInt(request.getParameter("category"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?err=1");
		}
		
		String name = request.getParameter("title");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		Category category = new Category(cat_id, null);
		javax.servlet.http.Part filePart = request.getPart("picture");
		
		final String dirPathName = request.getServletContext().getRealPath("/upload");
		
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();	
		}
	
		String fileName = FileUtil.getName(filePart);
		String picture = FileUtil.rename(fileName);
		String filePathName = dirPathName + File.separator + picture;
		News item = new News(0, name, preview, detail, picture, null, 0, category);
		
		if(newsDAO.addItem(item) >0) {
			if(!fileName.isEmpty()) {
				filePart.write(filePathName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=1");
			return;
		} else {
			ArrayList<Category> categories = categoryDAO.getItems();
			request.setAttribute("categories", categories);
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp?err=1");
			rd.forward(request, response);
			return;

		}
}

}
