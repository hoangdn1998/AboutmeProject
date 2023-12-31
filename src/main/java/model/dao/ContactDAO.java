package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Contact;
import util.DBConnectionUtil;
import util.DefineUtil;

public class ContactDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<Contact> getItems() {
		ArrayList<Contact> items = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM contacts";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Contact item = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("email"), rs.getString("address"), rs.getString("message"));
				items.add(item);
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
		return items;
	}

	public int addItem(Contact item) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="INSERT INTO contacts (name,phone,email,address,message) VALUE(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getContact_name());
			pst.setString(2, item.getPhone());
			pst.setString(3, item.getEmail());
			pst.setString(4, item.getAddress());
			pst.setString(5, item.getMessage());
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

	public Contact getItem(int id) {
		Contact item = null;
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM contacts WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("email"), rs.getString("address"), rs.getString("message"));
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
		return item;
	}

	public int editItem(Contact item) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="UPDATE contacts SET name = ?,phone = ?,email = ?, address = ?,message = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getContact_name());
			pst.setString(2, item.getPhone());
			pst.setString(3, item.getEmail());
			pst.setString(4, item.getAddress());
			pst.setString(5, item.getMessage());
			pst.setInt(6, item.getContact_id());
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

	public int delItem(int id) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="DELETE FROM contacts  WHERE id = ?";
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
		String query ="SELECT COUNT(*) AS count FROM contacts" ;
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

	public ArrayList<Contact> getItemsPagination(int offset) {
		ArrayList<Contact> items = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM contacts ORDER BY id DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, offset);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Contact item = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("email"), rs.getString("address"), rs.getString("message"));
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
		String query ="SELECT COUNT(*) AS count FROM contacts WHERE name LIKE ?" ;
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

	public ArrayList<Contact> getItemsPagination(int offset, String name) {
		ArrayList<Contact> items = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM contacts WHERE name LIKE ?  ORDER BY id DESC LIMIT ?,? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, "%" + name + "%");
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Contact item = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("phone"),
						rs.getString("email"), rs.getString("address"), rs.getString("message"));
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
