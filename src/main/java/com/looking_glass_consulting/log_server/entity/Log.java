package com.looking_glass_consulting.log_server.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.looking_glass_consulting.log_server.entity.dto.LogDTO;

@Entity
@Table(name = "logs")
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
	private int id;
	
	@ManyToOne(cascade = {
		CascadeType.DETACH,
		CascadeType.MERGE,
		CascadeType.PERSIST,
		CascadeType.REFRESH
	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "call_id")
	private Call call;
	
	@ManyToOne(cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
		}, fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "date")
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	public Log () {}

	public Log(Call call, User listener, LocalDate date) {
		this.call = call;
		this.user = listener;
		this.date = date;
	}
	
	public Log(LogDTO logDTO) {
		this.id = logDTO.getId();
		this.call = new Call(logDTO.getCall());
		this.user = logDTO.getUser();
		this.date = LocalDate.parse(logDTO.getDate());
	}

	public int getId() {
		return id;
	}

	public void setId(int logId) {
		this.id = logId;
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Log [logId=" + id + ", call=" + call + ", listener=" + user + ", date=" + date + "]";
	}
}
