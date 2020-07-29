package com.looking_glass_consulting.log_server.entity.dto;

import java.time.LocalDate;

import com.looking_glass_consulting.log_server.entity.Call;
import com.looking_glass_consulting.log_server.entity.Client;
import com.looking_glass_consulting.log_server.entity.SalesPerson;

public class CallDTO {
	
	private int callId;
	private LocalDate date;
	private String duration;
	private SalesPerson salesPerson;
	private Client client;

	public CallDTO() {}
	
	public CallDTO(Call call) {
		this.callId = call.getId();
		this.date = call.getDate();
		this.duration = call.getDuration();
		this.salesPerson = call.getSalesPerson();
		this.client = call.getClient();
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public String getDate() {
		return date.toString();
	}

	public void setDate(String dateString) {
		dateString = dateString.split("T")[0];
		this.date = LocalDate.parse(dateString);
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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
