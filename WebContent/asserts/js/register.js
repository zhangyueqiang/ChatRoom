/**
 * 
 */

function onRegister(){
		var username = $('#username').val();
		var password = $('#password').val();
		var nickname = $('#nickname').val();
		var user = {
				username: username, 
				password: password,
				nickname: nickname,
		};
		console.log(JSON.stringify(user));
		$.ajax({
			url: 'register',
			type: 'POST',
			data: JSON.stringify(user),
			dataType: 'JSON',
			contentType: 'application/json;charset=utf-8',
			success: function(msg) {
				if (msg.status != null && msg.status == "success") {
					window.location.href = 'Chat';
				}else {
					alert ('注册失败！' + '原因是：\n' + msg.reason);
				}
			},
			error :function(msg) {
				console.log('ajax发生错误'+msg);
			}
		})
		return false;
}

