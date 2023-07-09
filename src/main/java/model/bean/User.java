package model.bean;

public class User {
	private int user_id;
	private String username;
	private String password;
	private String fullname;
	private String picture;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public User(int user_id, String username, String password, String fullname, String picture) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.picture = picture;
	}
	public User() {
		super();
	}
	
	
}
