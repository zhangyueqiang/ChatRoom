package com.zyq.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.zyq.models.ChatMessage;
import com.zyq.models.User;
import com.zyq.service.UserServiceImpl;

@ServerEndpoint("/chat/{username}")
public class ChatController {
	private final String MessageTypeSystemNotify = "SystemNotify";
	private final String MessageTypeChatMessage = "ChatMessage";
	private static List<ChatController> connectedUsers = new CopyOnWriteArrayList<>();
	private Session session;
	private User user;
	public static List<ChatController> getConnectedUsers() {
		return connectedUsers;
	}
	public User getUser() {
		return user;
	}
	@OnOpen
	public void onOpen(Session session, @PathParam(value="username") String username) {
		this.session = session;
		User user = new User();
		UserServiceImpl impl=new UserServiceImpl();
		user.setUsername(username);
		this.user = impl.queryUserWithUserName(username);
		user.setPassword(null);
		connectedUsers.add(this);
		sendNotifyMessage();
		System.out.println(user.getUsername() + "上线！");
	}
	@OnClose
	public void onClose(){
		connectedUsers.remove(this);
		sendNotifyMessage();
		System.out.println(user.getUsername() + "下线！");
		// 下线后，应该发送通知
	}
	@OnMessage
	public void onMessage(String message, Session session){
		try {
			JSONObject messageJson = new JSONObject(message);
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.readFromJson(messageJson);
			if (chatMessage.getFrom() == null) {
				return;
			}
			chatMessage.setMessageType(MessageTypeChatMessage);
			chatMessage.setFromNick(user.getNickname());
			if (chatMessage.getTo() == null) { // 全局群聊消息
				for (ChatController chatController : connectedUsers) {
					if (chatController.user.getUsername().trim().equals(chatMessage.getFrom())) {
						continue;
					}
				String msgContent = chatMessage.toJson().toString();
				chatController.sendMessageText(msgContent);	
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@OnError
	public void onError(Throwable throwalble) {
		System.out.println(throwalble.getMessage());
	}
	private void sendMessageText(String content) throws IOException {
		this.session.getBasicRemote().sendText(content);
	}
	private void sendNotifyMessage(){
		ChatMessage message = new ChatMessage();
		message.setMessageType(MessageTypeSystemNotify);
		message.setMessageContent(user.toString());
		message.setFrom("system");
		message.setTo(null);
		if (connectedUsers.size() > 0) {
			try {
				connectedUsers.iterator().next().session.getBasicRemote().sendText(message.toJson().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}