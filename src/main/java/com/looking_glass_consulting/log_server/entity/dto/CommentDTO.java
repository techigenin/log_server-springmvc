package com.looking_glass_consulting.log_server.entity.dto;

import com.looking_glass_consulting.log_server.entity.Comment;

public class CommentDTO {
	
	private int commentId;
	private LogDTO logDTO;
	private String comment;
	private String statement;
	private String timeString;

	public CommentDTO() {}
	
	public CommentDTO(Comment comment) {
		this.commentId = comment.getId();
		this.logDTO = new LogDTO(comment.getLog());
		this.comment = comment.getComment();
		this.statement = comment.getStatement();
		this.timeString = comment.getTime().toString();
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public LogDTO getLogDTO() {
		return logDTO;
	}

	public void setLogDTO(LogDTO logDTO) {
		this.logDTO = logDTO;
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

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
}
