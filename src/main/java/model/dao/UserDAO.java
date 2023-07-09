package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.User;
import util.DBConnectionUtil;
import util.DefineUtil;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public User existUser(String username, String password) {
		User item = null;
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM users WHERE username = ? AND password =?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				 item = new User(rs.getInt("user_id"), username, password, rs.getString("fullname"), rs.getString("picture"));
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

	public ArrayList<User> getItems() {
			ArrayList<User> items = new ArrayList<User>();
			conn = DBConnectionUtil.getConnection();
			String query ="SELECT * FROM users ";
			try {
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while(rs.next()) {
					User item = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e) {
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
			
			return items;
	}

	public int addItem(User item) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="INSERT INTO users(username,password,fullname,picture) VALUES(?,?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getUsername());
			pst.setString(2, item.getPassword());
			pst.setString(3, item.getFullname());
			pst.setString(4, item.getPicture());
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

	public boolean haveUser(String username) {
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1,username);
			rs = pst.executeQuery();
			if(rs.next()) {
				return true;
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
		return false;
	}

	public User getItem(int id) {
		User item = null;
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM users WHERE user_id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				 item = new User(id, rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"));
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

	public int editItem(User item) {
			int result =0; 
			conn = DBConnectionUtil.getConnection();
			String query ="UPDATE users SET fullname =?,password =?, picture =? WHERE user_id =?";
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, item.getFullname());
				pst.setString(2, item.getPassword());
				pst.setString(3, item.getPicture());
				pst.setInt(4, item.getUser_id());
				result = pst.executeUpdate();
			} catch (NullPointerException |SQLException e ) {
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
		String query ="DELETE FROM users  WHERE user_id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
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

	public int getId(String username) {
		int user_id =1;
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM users WHERE username = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1,username);
			rs = pst.executeQuery();
			if(rs.next()) {
				user_id = rs.getInt("user_id");
				return user_id;
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
		return user_id;
	}

	public int getNumberOfItems() {
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT COUNT(*) AS count FROM users" ;
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

	public ArrayList<User> getItemsPagination(int offset) {
		ArrayList<User> items = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM users ORDER BY user_id DESC LIMIT ?,? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				User item = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"));
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
		String query ="SELECT COUNT(*) AS count FROM users WHERE fullname LIKE ?" ;
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

	public ArrayList<User> getItemsPagination(int offset, String name) {
		ArrayList<User> items = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM users WHERE fullname LIKE ?  ORDER BY user_id DESC LIMIT ?,? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, "%" + name + "%");
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				User item = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("picture"));
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
