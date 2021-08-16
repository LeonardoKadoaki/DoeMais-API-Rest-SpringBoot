package com.doemais.api.dto;

import com.doemais.api.enums.MessageEnum;

public class MessageMoedasObjectType {

	private String message;
	private MessageEnum messageStatus;
	private long moedas;

	public MessageMoedasObjectType() {
	}

	public MessageMoedasObjectType(String message, MessageEnum messageStatus, long moedas) {
		this.message = message;
		this.messageStatus = messageStatus;
		this.moedas = moedas;
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

	public long getMoedas() {
		return moedas;
	}

	public void setMoedas(long moedas) {
		this.moedas = moedas;
	}

}
