package com.muchenski.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Instant timestamp;
	private HttpStatus status;
	private String message;
	private String path;

	public StandardError() {

	}

	public StandardError(HttpStatus status, String message, String path) {
		this.timestamp = Instant.now();
		this.status = status;
		this.message = message;
		this.path = path;
	}

	public Integer getStatus() {
		return status.value();
	}

	public String getError() {
		return status.getReasonPhrase();
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
