<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<title>用户注册</title>
<link rel="stylesheet" type="text/css" href="asserts/css/register.css">
<script src="asserts/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="asserts/js/register.js"></script>
</head>
<body>
	<div class="container">
		<div class="register-area">
			<span class="register-title">
				用户注册
			</span>
			<form method="post" onsubmit="return checkInput();" class="fm-register">
				<div class="fm-group"><span class="input-info">用户名</span><input type="text" placeholder="请输入用户名" name="username" id="username" required onfocus="userRefocus(this)" onblur="invalideUser(this)" autocomplete="off"><a class="username-status status-indicator icon-yes"></a></div>
				<div class="fm-group"><span class="input-info">昵称</span><input type="text" placeholder="请输入昵称" name="nickname" id="nickname" required onfocus="nicknameRefocus(this)" onblur="invalideNickname(this)" autocomplete="off"><a class="nickname-status status-indicator icon-yes"></a></div>
				<div class="fm-group"><span class="input-info">密码</span><input type="password" id="password" name="password" placeholder="请输入密码" required onfocus="passwordRefocus(this)" onblur="invalidePassword(this)" autocomplete="off"><a class="password-status status-indicator icon-yes"></a></div>
				<div class="operate-grid">
					<input type="submit" id="register" value="立即注册" class="btn btn-primary" disabled="disabled" autocomplete="off">
					<a class="notify">*请填写每一项</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>