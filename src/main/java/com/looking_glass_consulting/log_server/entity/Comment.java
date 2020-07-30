package com.looking_glass_consulting.log_server.entity;

import java.time.LocalTime;

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

import com.looking_glass_consulting.log_server.entity.dto.CommentDTO;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private int id;
	
	@ManyToOne(cascade = {
		CascadeType.DETACH,
		CascadeType.MERGE,
		CascadeType.PERSIST,
		CascadeType.REFRESH
	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "log_id")
	private Log log;

	@Column(name="concern_level")
	private String concernLvl;
	
	@Column(name="reason")
	private Character reason;
	
	@Column(name = "statement")
	private String statement;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "time")
	private LocalTime time;
		
	public Comment () {}

	public Comment(Log log, String comment, String statement, LocalTime time) {
		this.log = log;
		this.comment = comment;
		this.statement = statement;
		this.time = time;
	}
	
	public Comment(CommentDTO commentDTO) {
		this.id = commentDTO.getId();
		this.log = new Log(commentDTO.getLog());
		this.comment = commentDTO.getComment();
		this.reason = commentDTO.getReason();
		this.statement = commentDTO.getStatement();
		this.time = LocalTime.parse(commentDTO.getTime());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getConcernLvl() {
		return concernLvl;
	}

	public void setConcernLvl(String concernLvl) {
		this.concernLvl = concernLvl;
	}

	public Character getReason() {
		return reason;
	}

	public void setReason(Character reason) {
		this.reason = reason;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", log=" + log + ", concernLvl=" + concernLvl + ", reason=" + reason
				+ ", statement=" + statement + ", comment=" + comment + ", time=" + time + "]";
	}
}
