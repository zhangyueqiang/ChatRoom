package com.zyq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zyq.models.User;
import com.zyq.service.UserServiceImpl;
import com.zyq.utils.ResponseInformation;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends JsonServlet {
	public static final String LOGINED_USER_SESSION_ATTR = "logined_user";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginData = ReadFromStream(request);
		try {
			JSONObject loginUserJson = new JSONObject(loginData);
			System.out.println("json信息"+loginUserJson);
			User user = new User();
			UserServiceImpl impl=new UserServiceImpl();
			user.readFromJson(loginUserJson);
			if (impl.authenticateUser(user)){
				PrintWriter writer = response.getWriter();
				writer.println(ResponseInformation.getSuccessInformation());
				writer.close();
				User loginedUser = impl.queryUserWithUserName(user.getUsername());
				request.getSession().setAttribute(LOGINED_USER_SESSION_ATTR, loginedUser);
			}else {
				PrintWriter writer = response.getWriter();
				writer.println(ResponseInformation.getErrorInformation("用户名或密码错误！"));
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
