package model.bean;

public class Skill {
	private int skill_id;
	private String name;
	private int skill;
	private int user_id;
	
	public int getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSkill() {
		return skill;
	}
	public void setSkill(int skill) {
		this.skill = skill;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public Skill(int skill_id, String name, int skill, int user_id) {
		super();
		this.skill_id = skill_id;
		this.name = name;
		this.skill = skill;
		this.user_id = user_id;
	}
	public Skill() {
		super();
	}
	
	
}
