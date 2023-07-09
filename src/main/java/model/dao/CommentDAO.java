package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.bean.Comment;
import util.DBConnectionUtil;

public class CommentDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public void addItems(Comment objComment) {
		conn = DBConnectionUtil.getConnection();
		String query ="INSERT INTO comments(name,comment,date_create,id_news) VALUES(?,?,?,?) ";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1,objComment.getName());
			pst.setString(2,objComment.getComment());
			pst.setTimestamp(3,objComment.getDate_create());
			pst.setInt(4,objComment.getId_news());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Comment getItem(int id,int limit) {
		Comment item = null;
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM comments WHERE id_news = ? ORDER BY id_comment DESC LIMIT ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			pst.setInt(2, limit);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Comment(rs.getInt("id_comment"), rs.getString("name"), rs.getString("comment"),rs.getTimestamp("date_create"), rs.getInt("id_news"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null ) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return item;
	}

	public ArrayList<Comment> getItems(int id, int limit) {
		ArrayList<Comment> items = new ArrayList<Comment>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM comments WHERE id_news = ? ORDER BY id_comment DESC LIMIT ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			pst.setInt(2, limit);
			rs = pst.executeQuery();
			while(rs.next()) {
				Comment item = new Comment(rs.getInt("id_comment"), rs.getString("name"), rs.getString("comment"),rs.getTimestamp("date_create"), rs.getInt("id_news"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null ) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return items;
	}

}
