package model.bean;

import java.sql.Timestamp;

public class News {
	private int news_id;
	private String name;
	private String preview;
	private String detail;
	private String picture;
	private Timestamp date_create;
	private int counter;
	private Category category;
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public News(int news_id, String name, String preview, String detail, String picture, Timestamp date_create,
			int counter, Category category) {
		super();
		this.news_id = news_id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.picture = picture;
		this.date_create = date_create;
		this.counter = counter;
		this.category = category;
	}
	public News() {
		super();
	}
	
	
	
	
	
}
