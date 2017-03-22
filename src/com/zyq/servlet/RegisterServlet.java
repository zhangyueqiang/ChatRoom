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
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String postData = ReadFromStream(request);
		if (postData==null) {
			String responseStr = ResponseInformation.getErrorInformation("��������Ϊ�գ�");
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
					writer.println(ResponseInformation.getErrorInformation("�û����Ѵ���"));
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
				writer.println(ResponseInformation.getErrorInformation("δ֪�Ĵ���"));
				writer.close();
				return;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			writer.println(ResponseInformation.getErrorInformation("ϵͳ�쳣����"+e.getMessage()));
			writer.close();
		}
	}
}
