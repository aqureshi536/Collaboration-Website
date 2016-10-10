package com.ahmad.model;

import com.ahmad.utility.IdGenerator;

public class Message {
	private String messageId;
	private String chatMessage;

	public Message( String chatMessage) {
		this.messageId = IdGenerator.generateId("MSG");
		this.chatMessage = chatMessage;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}

}
