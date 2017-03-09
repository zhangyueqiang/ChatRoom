package com.zyq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.zyq.models.User;
import com.zyq.service.UserServiceImpl;
import com.zyq.utils.ResponseInformation;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends JsonServlet {
	public static final String LOGINED_USER_SESSION_ATTR = "logined_user";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String postData = ReadFromStream(request);
		if (postData==null) {
			String responseStr = ResponseInformation.getErrorInformation("数据请求为空！");
			writer.println(responseStr);
			writer.close();
			return;
		}
		try {
			JSONObject userJson = new JSONObject(postData);
			User user = new User(); 
			UserServiceImpl impl=new UserServiceImpl();
			user.readFromJson(userJson);
			try {
				User checkedUser = impl.queryUserWithUserName(user.getUsername());
				if (checkedUser!=null) {
					writer.println(ResponseInformation.getErrorInformation("用户名已存在"));
					writer.close();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (impl.registerUser(user)){
				writer.println(ResponseInformation.getSuccessInformation());
				request.getSession().setAttribute(LOGINED_USER_SESSION_ATTR, user);
				writer.close();
				return;
			}else {
				writer.println(ResponseInformation.getErrorInformation("未知的错误"));
				writer.close();
				return;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			writer.println(ResponseInformation.getErrorInformation("系统异常错误！"+e.getMessage()));
			writer.close();
		}
	}
}
