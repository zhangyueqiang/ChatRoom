package com.zyq.models;


import org.json.JSONObject;

public class User implements IJsonSeriserialize{
	private String username;	//”√ªß√˚
	private String password;	//√‹¬Î
	private String nickname;	//Í«≥∆
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String NICKNAME = "nickname";
	public User() {
		super();
	}
	public User(String username, String password, String nickname) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", nickname=" + nickname + "]";
	}
	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
			json.put(USERNAME, username);
			json.put(NICKNAME, nickname);
			json.put(PASSWORD, password);
		return json;
	}
	@Override
	public void readFromJson(JSONObject json) {
		if (json.has(USERNAME))
			this.username=json.getString(USERNAME);
		if (json.has(PASSWORD))
			this.password=json.getString(PASSWORD);
		if (json.has(NICKNAME))
			this.nickname=json.getString(NICKNAME);
	}
}
