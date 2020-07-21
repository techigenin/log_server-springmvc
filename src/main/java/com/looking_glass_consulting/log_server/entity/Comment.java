package com.looking_glass_consulting.log_server.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.looking_glass_consulting.log_server.entity.dto.CommentDTO;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@Column(name = "comment_id")
	private int commentId;
	
	@ManyToOne(cascade = {
		CascadeType.DETACH,
		CascadeType.MERGE,
		CascadeType.PERSIST,
		CascadeType.REFRESH
	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "log_id")
	private Log log;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "statement")
	private String statement;
	
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

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
		this.commentId = commentDTO.getCommentId();
		this.log = new Log(commentDTO.getLogDTO());
		this.comment = commentDTO.getComment();
		this.statement = commentDTO.getStatement();
		this.time = LocalTime.parse(commentDTO.getTimeString());
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
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
		return "Comment [commentId=" + commentId + ", log=" + log + ", comment=" + comment + ", time=" + time + "]";
	}
	
	
}
