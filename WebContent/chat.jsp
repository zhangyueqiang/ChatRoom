<%@page import="com.zyq.servlet.LoginServlet"%>
<%@page import="com.zyq.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Object sessionObj = request.getSession().getAttribute(LoginServlet.LOGINED_USER_SESSION_ATTR);
User loginedUser = null;
if (null != sessionObj) {
	if (sessionObj instanceof User)
		loginedUser = (User)sessionObj;
}else {
	request.getRequestDispatcher("login.jsp").forward(request, response);  // 重定向
}
%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="asserts/css/chat.css">
<script src="asserts/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
var loginedUsername = '<%=loginedUser.getUsername()%>';
var loginedUsernick = '<%=loginedUser.getNickname()%>'
</script>
<script type="text/javascript" src="asserts/js/chat.js"></script>
<title>聊天室</title>
</head>
<body> 
    
	<div class="container">
		<div class="header-nav">
			<a id="chat-title">群聊中...</a>
		</div>
		<div class="content-top">
		     <span>
					<span class="chat-username"><%=loginedUser.getNickname()%></span>
					<a class="chat-title" id="switch-list">用户列表</a>
					<a class="chat-close" id="logout">退出</a>
			</span>
			<div class="online-list" id="online-list">
				<div class="panel-nav">
					<a>在线列表</a>
					<a class="opt-close" href="javascript:;" id="opt-close">关闭</a>
				</div>
				<div class="contact-list">
					<ul class="users-list">
						
					</ul>
				</div>
			</div>
	  </div>
		<div class="content-message">
            <ul class="messages-list">
            
            </ul>
            <a class="bottom-anchors"></a>
		</div>
		<div class="message-operate">
			<form method="post" onsubmit="return sendMessage();" class="fm-message">
				<input type="text" id="message-content" class="message-input" placeholder="输入消息内容" autocomplete="off">
			</form>
			<input type="text" value="发送" id="send-message" class="message-send"></input>
		</div>
	</div>
</body>
</html>