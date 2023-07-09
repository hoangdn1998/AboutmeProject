package model.bean;

public class Target {
	private int target_id;
	private String targetname;
	private String target;
	private int user_id;
	
	public int getTarget_id() {
		return target_id;
	}
	public void setTarget_id(int target_id) {
		this.target_id = target_id;
	}
	public String getTargetname() {
		return targetname;
	}
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public Target(int target_id, String targetname, String target, int user_id) {
		super();
		this.target_id = target_id;
		this.targetname = targetname;
		this.target = target;
		this.user_id = user_id;
	}
	public Target() {
		super();
	}
	
	
}
