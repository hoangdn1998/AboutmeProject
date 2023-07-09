package model.bean;

public class Project {
	private int project_id;
	private String name;
	private String preview;
	private String link;
	private String picture;
	private int user_id;
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Project(int project_id, String name, String preview, String link, String picture, int user_id) {
		super();
		this.project_id = project_id;
		this.name = name;
		this.preview = preview;
		this.link = link;
		this.picture = picture;
		this.user_id = user_id;
	}
	public Project() {
		super();
	}

	
}
