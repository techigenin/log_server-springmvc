package com.looking_glass_consulting.log_server.entity.dto;

import java.time.LocalDate;

import com.looking_glass_consulting.log_server.entity.Log;
import com.looking_glass_consulting.log_server.entity.User;

public class LogDTO {
	
	private int id;
	private CallDTO call;
	private User user;
	private LocalDate date;

	public LogDTO() {}
	
	public LogDTO(Log log) {
		this.id = log.getId();
		this.call = new CallDTO(log.getCall());
		this.user = log.getUser();
		this.date = log.getDate();
	}

	public int getId() {
		return id;
	}

	public void setId(int logId) {
		this.id = logId;
	}

	public CallDTO getCall() {
		return call;
	}

	public void setCall(CallDTO callDTO) {
		this.call = callDTO;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User listener) {
		this.user = listener;
	}

	public String getDate() {
		return date.toString();
	}

	public void setDate(String dateString) {
		dateString = dateString.split("T")[0];
		this.date = LocalDate.parse(dateString);
	}
}
