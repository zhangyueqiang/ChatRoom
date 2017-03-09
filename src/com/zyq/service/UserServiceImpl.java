package com.zyq.service;

import com.zyq.models.User;

public class UserServiceImpl {
	UserService service=new UserService();
	public boolean authenticateUser(User user) {
		return service.userExists(user);
	}
	public boolean authenticateUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return service.userExists(user);
	}
	public boolean registerUser(User user) {
		return service.addUser(user);
	}
	public User queryUserWithUserName(String username) {
		return service.queryUserWithName(username);
	}
}
