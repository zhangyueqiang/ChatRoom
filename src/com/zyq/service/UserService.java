package com.zyq.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zyq.db.DataBase;
import com.zyq.models.User;

public class UserService {
	private DataBase db=new DataBase();
	private final String sql1="select * from user where username=?";
	public boolean userExists(User user){
		if (!db.openConnection()){
			System.out.println("数据库连接池打开失败");
			return false;
		}
		try {
			Connection conn=db.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql1);
			String username=user.getUsername();
			ps.setString(1, username);
			ResultSet result=ps.executeQuery();
			while (result.next()){
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection();
		}
		return false;
	}
	private final String sql2="insert into user values(?,?,?)";
		public boolean addUser(User user){		
			if (userExists(user)==true){
				System.out.println("用户已存在，用户添加发生错误");
				return false;
			}
			if (!db.openConnection()){
				System.out.println("数据库连接池打开失败");
				return false;
			}
			try {
				Connection conn=db.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql2);
				String username=user.getUsername();
				String password=user.getPassword();
				String nickname=user.getNickname();
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, nickname);
				ps.executeUpdate();
				System.out.println("添加成功");
				return true;
			} catch (Exception e) {
				System.out.println(" 添加失败"+e.getMessage());
				return false;
			}finally {
				db.closeConnection();
			}
		}
		public User queryUserWithName(String username){
			if (!db.openConnection()){
				System.out.println("数据库连接池打开失败");
				return null;
			}
			try {
				Connection conn=db.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql1);
				ps.setString(1, username);
				ResultSet result=ps.executeQuery();
				while (result.next()){
					User user=new User();
					user.setUsername(result.getString("username"));
					user.setPassword(null);
					user.setNickname(result.getString("nickname"));
					return user;
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				db.closeConnection();
			}
		}
}
