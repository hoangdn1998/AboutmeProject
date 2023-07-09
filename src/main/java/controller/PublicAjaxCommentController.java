package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.dao.CommentDAO;

public class PublicAjaxCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO commentDAO;
       
    public PublicAjaxCommentController() {
        super();
        commentDAO = new CommentDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String comment = request.getParameter("acmt");
		String usercmt = request.getParameter("usercmt");
		int id = Integer.parseInt(request.getParameter("news_id"));

		int limit = 1;
		Comment objComment  = new Comment(0, usercmt, comment, null, id);
		commentDAO.addItems(objComment);
		Comment commentItem = commentDAO.getItem(id,limit);
		request.setAttribute("commentItem", commentItem);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/ajaxcomment.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
