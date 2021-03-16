package com.doemais.api.exception;

import java.util.Date;

public class ObjectError {

	private final int status; 
	private final String detail;
	private final Date timestamp;

	public ObjectError(int status, String detail, Date timestamp) {
		this.status = status;
		this.detail = detail;
		this.timestamp = timestamp;
	}

	public String getDetail() {
		return detail;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

}
