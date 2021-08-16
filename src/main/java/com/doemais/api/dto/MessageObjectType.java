package com.doemais.api.dto;

import com.doemais.api.enums.MessageEnum;

public class MessageObjectType {
	
	private String message;
	private MessageEnum messageStatus;
	
	public MessageObjectType() {
	}

	public MessageObjectType(String message, MessageEnum messageStatus) {
		this.message = message;
		this.messageStatus = messageStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageEnum getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageEnum messageStatus) {
		this.messageStatus = messageStatus;
	}

}
