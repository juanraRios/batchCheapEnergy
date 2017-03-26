package com.juanrarios.springbatch.rest.dto;

public class NotificationDTO {
	private String title;

	private String body;
	
	public NotificationDTO(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "NotificationDTO [title = " + title + ", body = " + body + "]";
	}
}