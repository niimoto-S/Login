package jp.co.aforce.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

	private String id;
	private String hashPassword;

	public LoginBean() {
	}

	public LoginBean(String id, String hashPassword) {
		this.id = id;
		this.hashPassword = hashPassword;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

}
