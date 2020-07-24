package com.looking_glass_consulting.log_server.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.looking_glass_consulting.log_server.entity.dto.CallDTO;


@Entity
@Table(name = "calls")
public class Call{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "call_id")
	private int id;
	
	@Column(name = "date")	
	private LocalDate date;
	
	@Column(name = "duration")
	private String duration;
	
	@OneToOne(cascade = 
			{CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JoinColumn(name="sales_person_id")
	private SalesPerson salesPerson; 
	
	@OneToOne(cascade = 
		{CascadeType.DETACH,
		CascadeType.MERGE,
		CascadeType.PERSIST,
		CascadeType.REFRESH},
		fetch = FetchType.LAZY)
	@JoinColumn(name="client_id")
	private Client client; 
	
	public Call() {}

	public Call(LocalDate date, String duration, SalesPerson salesPerson, Client client) {
		this.date = date;
		this.duration = duration;
		this.salesPerson = salesPerson;
		this.client = client;
	}
	
	public Call(CallDTO callDTO) {
		this.id = callDTO.getCallId();
		this.date = LocalDate.parse(callDTO.getDate());
		this.duration = callDTO.getDuration();
		this.salesPerson = callDTO.getSalesPerson();
		this.client = callDTO.getClient();
	}

	public int getId() {
		return id;
	}

	public void setId(int callId) {
		this.id = callId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "Call [callId=" + id + ", date=" + date + ", duration=" + duration + ", salesPerson=" + salesPerson
				+ ", client=" + client + "]";
	}
}
