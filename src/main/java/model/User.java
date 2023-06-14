package model;

public class User {
	private String userId;
	private String loginPassword;
	private String userNickname;
	private int chip;
	
	public User(String userId, String loginPassword, String userNickname) {
		this.userId = userId;
		this.loginPassword = loginPassword;
		this.userNickname = userNickname;
	}
	
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLoginPassword() {
		return loginPassword;
	}
	
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	public String getUserNickname() {
		return userNickname;
	}
	
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	public int getChip() {
		return chip;
	}
	
	public void setChip(int chip) {
		this.chip = chip;
	}

}
