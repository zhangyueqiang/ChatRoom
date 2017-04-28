package com.zyq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.zyq.controller.ChatController;
import com.zyq.models.User;
import com.zyq.service.UserServiceImpl;
import com.zyq.utils.ResponseInformation;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/userlist")
public class UserListServlet extends JsonServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		List<ChatController> usersOnline = ChatController.getConnectedUsers();
		JSONArray allUsers = new JSONArray();
		UserServiceImpl impl=new UserServiceImpl(); 
		for (ChatController chatController : usersOnline) {
			User user = impl.queryUserWithUserName(chatController.getUser().getUsername());
			System.out.println("用户列表"+user);
			if (null != user) {
				user.setPassword(null);
				allUsers.put(user.toJson());
			}
		}
		PrintWriter writer = response.getWriter();
		try {
			writer.println(allUsers.toString());
		} catch (Exception e) {
			writer.println(ResponseInformation.getErrorInformation("用户列表获取错误"));
		}finally {
			writer.close();
		}
	}
}
