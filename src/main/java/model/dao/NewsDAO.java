package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.News;
import util.DBConnectionUtil;
import util.DefineUtil;

public class NewsDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<News> getItems() {
		ArrayList<News> items = new ArrayList<News>(); 
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT n.news_id AS nid, n.name AS nname,preview,detail,picture,date_create,counter,n.category_id AS ncat_id,c.name AS cname "
				+ "FROM news AS n INNER JOIN categories AS c ON n.category_id = c.category_id "
				+ "ORDER BY n.news_id ASC";
		try {
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("nid");
				String name = rs.getString("nname");
				String preview= rs.getString("preview");
				String detail =rs.getString("detail");
				String picture = rs.getString("picture");
			    Timestamp date_create = rs.getTimestamp("date_create");
				int counter = rs.getInt("counter");
				int cat_id = rs.getInt("ncat_id");
				Category category = new Category(cat_id, rs.getString("cname"));
				News item = new News(id, name, preview, detail, picture, date_create, counter, category);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null) {
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

	public News getItem(int id) {
		News item = null; 
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT n.news_id AS nid, n.name AS nname,preview,detail,picture,date_create,counter,n.category_id AS ncat_id,c.name AS cname "
				+ "FROM news AS n INNER JOIN categories AS c ON n.category_id = c.category_id WHERE n.news_id  = ? "
				+ "ORDER BY n.news_id ASC";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String name = rs.getString("nname");
				String preview= rs.getString("preview");
				String detail =rs.getString("detail");
				String picture = rs.getString("picture");
			    Timestamp date_create = rs.getTimestamp("date_create");
				int counter = rs.getInt("counter");
				int cat_id = rs.getInt("ncat_id");
				Category category = new Category(cat_id, rs.getString("cname"));
				item = new News(id, name, preview, detail, picture, date_create, counter, category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null) {
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

	public int addItem(News item) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="INSERT INTO news (name,preview,detail,picture,date_create,counter,category_id) VALUES(?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreview());
			pst.setString(3, item.getDetail());
			pst.setString(4, item.getPicture());
			pst.setTimestamp(5, item.getDate_create());
			pst.setInt(6, item.getCounter());
			pst.setInt(7, item.getCategory().getCategory_id());
			result = pst.executeUpdate();
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
		return result;
	}

	public int editItem(News item) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="UPDATE news SET name =?,preview =?,detail =?,picture =?,category_id =? WHERE news_id =?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreview());
			pst.setString(3, item.getDetail());
			pst.setString(4, item.getPicture());
			pst.setInt(5, item.getCategory().getCategory_id());
			pst.setInt(6, item.getNews_id());
			result = pst.executeUpdate();
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
		return result;
	}

	public int delItem(int id) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="DELETE FROM news  WHERE news_id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public int getNumberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT COUNT(*) AS count FROM news" ;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			if(rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && st != null && conn != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public ArrayList<News> getItemsPagination(int offset) {
		ArrayList<News> items = new ArrayList<News>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT n.news_id AS nid, n.name AS nname,preview,detail,picture,date_create,counter,n.category_id AS ncat_id,c.name AS cname "
				+ "FROM news AS n INNER JOIN categories AS c ON n.category_id = c.category_id "
				+ "ORDER BY n.news_id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("nid");
				String name = rs.getString("nname");
				String preview= rs.getString("preview");
				String detail =rs.getString("detail");
				String picture = rs.getString("picture");
			    Timestamp date_create = rs.getTimestamp("date_create");
				int counter = rs.getInt("counter");
				int cat_id = rs.getInt("ncat_id");
				Category category = new Category(cat_id, rs.getString("cname"));
				News item = new News(id, name, preview, detail, picture, date_create, counter, category);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null) {
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
	
	public int getNumberOfItems(String name) {
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT COUNT(*) AS count FROM news WHERE name LIKE ?" ;
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, "%" + name + "%");
			rs = pst.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	

	public ArrayList<News> getItemsPagination(int offset, String newsName) {
		ArrayList<News> items = new ArrayList<News>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT n.news_id AS nid, n.name AS nname,preview,detail,picture,date_create,counter,n.category_id AS ncat_id,c.name AS cname "
				+ "FROM news AS n INNER JOIN categories AS c ON n.category_id = c.category_id "
				+ "WHERE n.name LIKE ? ORDER BY n.news_id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, "%" + newsName + "%");
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("nid");
				String preview= rs.getString("preview");
				String name = rs.getString("nname");
				String detail =rs.getString("detail");
				String picture = rs.getString("picture");
			    Timestamp date_create = rs.getTimestamp("date_create");
				int counter = rs.getInt("counter");
				int cat_id = rs.getInt("ncat_id");
				Category category = new Category(cat_id, rs.getString("cname"));
				News item = new News(id, name, preview, detail, picture, date_create, counter, category);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null) {
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

	public void increaseView(int news_id) {
		conn = DBConnectionUtil.getConnection();
		String query ="UPDATE news SET counter = counter + 1 WHERE news_id =?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, news_id);
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

	public ArrayList<News> getMaxItem(int limit) {
		ArrayList<News> items = new ArrayList<News>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT n.news_id AS nid, n.name AS nname,preview,detail,picture,date_create,counter,n.category_id AS ncat_id,c.name AS cname "
				+ "FROM news AS n INNER JOIN categories AS c ON n.category_id = c.category_id "
				+ " ORDER BY counter DESC LIMIT ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, limit);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("nid");
				String preview= rs.getString("preview");
				String name = rs.getString("nname");
				String detail =rs.getString("detail");
				String picture = rs.getString("picture");
			    Timestamp date_create = rs.getTimestamp("date_create");
				int counter = rs.getInt("counter");
				int cat_id = rs.getInt("ncat_id");
				Category category = new Category(cat_id, rs.getString("cname"));
				News item = new News(id, name, preview, detail, picture, date_create, counter, category);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null) {
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

	public ArrayList<News> getItemByCat(int cid) {
		ArrayList<News> items = new ArrayList<News>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT n.news_id AS nid, n.name AS nname,preview,detail,picture,date_create,counter,n.category_id AS ncat_id,c.name AS cname "
				+ "FROM news AS n INNER JOIN categories AS c ON n.category_id = c.category_id "
				+ "WHERE n.category_id = ? ORDER BY n.news_id DESC";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("nid");
				String preview= rs.getString("preview");
				String name = rs.getString("nname");
				String detail =rs.getString("detail");
				String picture = rs.getString("picture");
			    Timestamp date_create = rs.getTimestamp("date_create");
				int counter = rs.getInt("counter");
				int cat_id = rs.getInt("ncat_id");
				Category category = new Category(cat_id, rs.getString("cname"));
				News item = new News(id, name, preview, detail, picture, date_create, counter, category);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null && pst != null && conn != null) {
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
