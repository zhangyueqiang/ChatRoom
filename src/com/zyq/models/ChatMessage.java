package com.zyq.models;

import org.json.JSONObject;

public class ChatMessage implements IJsonSeriserialize{
	private String from;	//消息发送的用户
	private String to;		//消息接受的用户
	private String messageContent;	//消息内容
	private String messageType;		
	/*消息类型：ChatMessage:聊天消息；SystemNotify:系统消息*/
	private String fromNick;	//消息发送用户的昵称
	public static final String FROM = "from";
	public static final String TO = "to";
	public static final String MESSSAGE_CONTENT = "messageContent";
	public static final String MESSAGE_TYPE = "messageType";
	public static final String FROM_NICKNAME = "fromNick";
	public ChatMessage() {
		super();
	}
	public ChatMessage(String from, String to, String messageContent) {
		super();
		this.from = from;
		this.to = to;
		this.messageContent = messageContent;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getFromNick() {
		return fromNick;
	}
	public void setFromNick(String fromNick) {
		this.fromNick = fromNick;
	}
	@Override
	public String toString() {
		return "ChatMessage [from=" + from + ", to=" + to + ", messageContent=" + messageContent + ", messageType="
				+ messageType + ", fromNick=" + fromNick + "]";
	}
	@Override
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put(FROM, from);
		json.put(TO, to);
		json.put(MESSSAGE_CONTENT, messageContent);
		json.put(MESSAGE_TYPE, messageType);
		json.put(FROM_NICKNAME, fromNick);
		return json;
	}
	@Override
	public void readFromJson(JSONObject json) {
			if (json.has(FROM)) {
				this.from = json.getString(FROM);
			}
			if (json.has(TO)) {
				this.to = json.getString(TO);
			}
			if (json.has(MESSSAGE_CONTENT)) {
				this.messageContent = json.getString(MESSSAGE_CONTENT);
			}
			if (json.has(MESSAGE_TYPE)) {
				this.messageType = json.getString(MESSAGE_TYPE);
			}
			if (json.has(FROM_NICKNAME)){
				this.fromNick = json.getString(FROM_NICKNAME);
			}
	}
}
