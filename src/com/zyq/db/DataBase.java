package com.zyq.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	DBInfo info = new DBInfo();
	Connection conn = null;
	public DataBase() {
		super();
	}
	public boolean openConnection()	{
		try {
			Class.forName(info.getDriver());
			conn = DriverManager.getConnection(info.getUrl(), info.getUsername(), info.getPassword());
			if (conn==null)
				return false;
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
	public boolean closeConnection() {
		if (conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	public Connection getConnection() {
		return conn;
	}
}
