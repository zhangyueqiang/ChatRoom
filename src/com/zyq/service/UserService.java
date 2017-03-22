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
		if (!db.openConnection()){   //������ݿ����Ӳ��ܴ򿪾�ֱ�ӷ���false
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
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			db.closeConnection();  //�ر����ݿ�����
		}
	}
	private final String sql2="insert into user values(?,?,?)";
		public boolean addUser(User user){	
			if (userExists(user)==true){  //������ݿ����и��û���Ϣ�����ܲ��������Ϣ
				return false;
			}
			if (!db.openConnection()){
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
				return true;
			} catch (Exception e) {
				System.out.println("���ʧ��"+e.getMessage());
				return false;
			}finally {
				db.closeConnection();
			}
		}
		public User queryUserWithName(String username){
			if (!db.openConnection()){
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
