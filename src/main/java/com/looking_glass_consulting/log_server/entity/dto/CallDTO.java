package com.looking_glass_consulting.log_server.entity.dto;

import com.looking_glass_consulting.log_server.entity.Call;
import com.looking_glass_consulting.log_server.entity.Client;
import com.looking_glass_consulting.log_server.entity.SalesPerson;

public class CallDTO {
	
	private int callId;
	private String dateString;
	private String durationString;
	private SalesPerson salesPerson;
	private Client client;

	public CallDTO() {}
	
	public CallDTO(Call call) {
		this.callId = call.getId();
		this.dateString = call.getDate().toString();
		this.durationString = call.getDuration().toString();
		this.salesPerson = call.getSalesPerson();
		this.client = call.getClient();
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String date) {
		this.dateString = date;
	}

	public String getDurationString() {
		return durationString;
	}

	public void setDurationString(String duration) {
		this.durationString = duration;
	}

	public SalesPerson getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(SalesPerson salesPerson) {
		this.salesPerson = salesPerson;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
