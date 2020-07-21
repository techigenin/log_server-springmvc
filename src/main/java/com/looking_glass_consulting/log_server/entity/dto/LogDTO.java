package com.looking_glass_consulting.log_server.entity.dto;

import com.looking_glass_consulting.log_server.entity.User;
import com.looking_glass_consulting.log_server.entity.Log;

public class LogDTO {
	
	private int logId;
	private CallDTO callDTO;
	private User listener;
	private String dateString;

	public LogDTO() {}
	
	public LogDTO(Log log) {
		this.logId = log.getLogId();
		this.callDTO = new CallDTO(log.getCall());
		this.listener = log.getUser();
		this.dateString = log.getDate().toString();
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public CallDTO getCallDTO() {
		return callDTO;
	}

	public void setCallDTO(CallDTO callDTO) {
		this.callDTO = callDTO;
	}

	public User getListener() {
		return listener;
	}

	public void setListener(User listener) {
		this.listener = listener;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
}
