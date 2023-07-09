package model.bean;

public class Infomation {
	private int inf_id;
	private String profile;
	private int year;
	private int numOfProject;
	private String phone;
	private String email;
	private String address;
	private User user;
	private Target target;
	private Skill skill;
	public int getInf_id() {
		return inf_id;
	}
	public void setInf_id(int inf_id) {
		this.inf_id = inf_id;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getNumOfProject() {
		return numOfProject;
	}
	public void setNumOfProject(int numOfProject) {
		this.numOfProject = numOfProject;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Infomation(int inf_id, String profile, int year, int numOfProject, String phone, String email,
			String address, User user, Target target, Skill skill) {
		super();
		this.inf_id = inf_id;
		this.profile = profile;
		this.year = year;
		this.numOfProject = numOfProject;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.user = user;
		this.target = target;
		this.skill = skill;
	}
	public Infomation() {
		super();
	}
	
	

	
}
