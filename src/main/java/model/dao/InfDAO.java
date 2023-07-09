package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Infomation;
import model.bean.Project;
import model.bean.Skill;
import model.bean.Target;
import model.bean.User;
import util.DBConnectionUtil;
import util.DefineUtil;

public class InfDAO {
	private Connection conn;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public Infomation getItem(String username) {
		Infomation item = null;
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT i.information_id AS inid, profile,years,projects,phone,email,address,i.user_id AS iuser_id,"
				+ "u.username AS uusername,u.fullname AS ufullname, u.picture AS upicture "
				+ "FROM infomations AS i INNER JOIN users AS u ON i.user_id = u.user_id WHERE u.username  = ? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				int inf_id = rs.getInt("inid");
				String profile = rs.getString("profile");
				int years = rs.getInt("years");
				int projects = rs.getInt("projects");
				String phone= rs.getString("phone");
				String email =rs.getString("email");
				String address = rs.getString("address");
			    int user_id = rs.getInt("iuser_id");
			    String user_username =rs.getString("uusername");
			    String fullname = rs.getString("ufullname");
			    String picture = rs.getString("upicture");
				User user = new User(user_id, user_username, null, fullname, picture);
				Target target = new Target();
				Skill skill = new Skill();
				item = new Infomation(inf_id, profile, years, projects, phone, email, address, user, target, skill);
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

	public ArrayList<Skill> getSkill(int user_id) {
		ArrayList<Skill> items= new  ArrayList<Skill>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM skills WHERE user_id  = ? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				int skill_id = rs.getInt("skill_id");
				String name = rs.getString("name");
				int skill = rs.getInt("skill");
			    Skill item = new Skill(skill_id, name, skill, user_id);
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

	public ArrayList<Target> getTarget(int user_id) {
		ArrayList<Target> items= new  ArrayList<Target>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM targets WHERE user_id  = ? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				int target_id = rs.getInt("target_id");
				String target_name = rs.getString("target_name");
				String target = rs.getString("target");
			    Target item = new Target(target_id, target_name, target, user_id);
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
	public ArrayList<Project> getProject(int user_id) {
		ArrayList<Project> items= new  ArrayList<Project>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM projects WHERE user_id  = ? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				int project_id = rs.getInt("project_id");
				String name = rs.getString("name");
				String preview = rs.getString("preview");
				String link = rs.getString("link");
				String picture = rs.getString("picture");
				Project item = new Project(project_id, name, preview, link, picture, user_id);
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

	public int addItem(Project item) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="INSERT INTO projects (name,preview,link,picture,user_id) VALUES(?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreview());
			pst.setString(3, item.getLink());
			pst.setString(4, item.getPicture());
			pst.setInt(5, item.getUser_id());
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

	public Project getItem(int id) {
		Project item = null;
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM projects WHERE project_id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				 item = new Project(rs.getInt("project_id"), rs.getString("name"), rs.getString("preview"), rs.getString("link"), rs.getString("picture"), rs.getInt("user_id"));
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

	public int editItem(Project item) {
		int result =0; 
		conn = DBConnectionUtil.getConnection();
		String query ="UPDATE projects SET name =?,preview =?,link =?, picture =?,user_id = ? WHERE project_id = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreview());
			pst.setString(3, item.getLink());
			pst.setString(4, item.getPicture());
			pst.setInt(5, item.getUser_id());
			pst.setInt(6, item.getProject_id());
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
		String query ="DELETE FROM projects  WHERE project_id = ?";
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

	public int getNumberOfProjectItems() {
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT COUNT(*) AS count FROM projects" ;
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


	public ArrayList<Project> getItemsPagination(int offset, int user_id) {
		ArrayList<Project> items = new ArrayList<Project>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM projects WHERE user_id = ? ORDER BY project_id DESC LIMIT ?,? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, user_id);
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				Project item = new Project(rs.getInt("project_id"), rs.getString("name"), rs.getString("preview"), rs.getString("link"), rs.getString("picture"), rs.getInt("user_id"));
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
		String query ="SELECT COUNT(*) AS count FROM projects WHERE name LIKE ?" ;
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

	public ArrayList<Project> getItemsPagination(int offset, String name) {
		ArrayList<Project> items = new ArrayList<Project>();
		conn = DBConnectionUtil.getConnection();
		String query ="SELECT * FROM projects WHERE name LIKE ?  ORDER BY project_id DESC LIMIT ?,? ";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, "%" + name + "%");
			pst.setInt(2, offset);
			pst.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Project item = new Project(rs.getInt("project_id"), rs.getString("name"), 
						rs.getString("preview"), rs.getString("link"), rs.getString("picture"), rs.getInt("user_id"));
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
