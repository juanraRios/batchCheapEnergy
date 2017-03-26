package com.juanrarios.springbatch.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheapestHourNotificationRequestDTO {
	@JsonProperty("to")
	private String to;

	@JsonProperty("notification")
	private NotificationDTO notificationDTO;
	
	public CheapestHourNotificationRequestDTO(String to, NotificationDTO notificationDTO) {
		super();
		this.to = to;
		this.notificationDTO = notificationDTO;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public NotificationDTO getNotification() {
		return notificationDTO;
	}

	public void setNotification(NotificationDTO notification) {
		this.notificationDTO = notification;
	}

	@Override
	public String toString() {
		return "CheapestHourNotificationRequestDTO [to = " + to + ", notification = " + notificationDTO + "]";
	}
}