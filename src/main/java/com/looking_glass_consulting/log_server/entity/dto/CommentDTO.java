package com.looking_glass_consulting.log_server.entity.dto;

import com.looking_glass_consulting.log_server.entity.Comment;

public class CommentDTO {
	
	private int id;
	private LogDTO log;
	private String concernLvl;
	private String comment;
	private String statement;
	private String time;

	public CommentDTO() {}
	
	public CommentDTO(Comment comment) {
		this.id = comment.getId();
		this.log = new LogDTO(comment.getLog());
		this.concernLvl = comment.getConcernLvl();
		this.comment = comment.getComment();
		this.statement = comment.getStatement();
		this.time = comment.getTime().toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LogDTO getLog() {
		return log;
	}

	public void setLog(LogDTO log) {
		this.log = log;
	}

	public String getConcernLvl() {
		return concernLvl;
	}

	public void setConcernLvl(String concernLvl) {
		this.concernLvl = concernLvl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", log=" + log + ", concernLvl=" + concernLvl + ", comment=" + comment
				+ ", statement=" + statement + ", time=" + time + "]";
	}
}
