package com.zyq.db;

public class DBInfo {
	private final String url = "jdbc:mysql://localhost:3306/dbchatroom?useUnicode=true&characterEncoding=utf-8&useSSL=false";	
	private final String username = "root";											
	private final String password = "123456";									
	private final String driver = "com.mysql.jdbc.Driver";							
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getDriver() {
		return driver;
	}
}
