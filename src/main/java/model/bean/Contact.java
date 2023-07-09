package model.bean;

public class Contact {
	private int contact_id; 
	private String contact_name;
	private String phone;
	private String email;
	private String address;
	private String message;
	public int getContact_id() {
		return contact_id;
	}
	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Contact(int contact_id, String contact_name, String phone, String email, String address, String message) {
		super();
		this.contact_id = contact_id;
		this.contact_name = contact_name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.message = message;
	}
	public Contact() {
		super();
	}
	
	
}
