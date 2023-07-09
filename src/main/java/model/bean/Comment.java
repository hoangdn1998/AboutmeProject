package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int id_comment;
	private String name;
	private String comment;
	private Timestamp date_create;
	private int id_news;
	public int getId_comment() {
		return id_comment;
	}
	public void setId_comment(int id_comment) {
		this.id_comment = id_comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public int getId_news() {
		return id_news;
	}
	public void setId_news(int id_news) {
		this.id_news = id_news;
	}
	public Comment(int id_comment, String name, String comment, Timestamp date_create, int id_news) {
		super();
		this.id_comment = id_comment;
		this.name = name;
		this.comment = comment;
		this.date_create = date_create;
		this.id_news = id_news;
	}
	public Comment() {
		super();
	}

	
	
}
