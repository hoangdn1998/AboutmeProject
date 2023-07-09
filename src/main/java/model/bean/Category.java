package model.bean;

public class Category {
	private int Category_id;
	private String name;
	public int getCategory_id() {
		return Category_id;
	}
	public void setCategory_id(int category_id) {
		Category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category(int category_id, String name) {
		super();
		Category_id = category_id;
		this.name = name;
	}
	public Category() {
		super();
	}
	
	
	
	
}
