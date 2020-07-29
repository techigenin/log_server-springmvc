package com.looking_glass_consulting.log_server.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.looking_glass_consulting.log_server.entity.Comment;
import com.looking_glass_consulting.log_server.entity.dto.CommentDTO;
import com.looking_glass_consulting.log_server.service.DbService;

@RestController
@RequestMapping("/api")
public class CommentRestController {

	@Autowired
	private DbService<Comment> commentService;
	
	@GetMapping("/comments")
	public List<CommentDTO> getComments() {
		List<Comment> comments = commentService.get();
		List<CommentDTO> commentDTOs = new ArrayList<CommentDTO>();
		
		for (Comment comment : comments) {
			commentDTOs.add(new CommentDTO(comment));
		}
		
		return commentDTOs;
	}
	
	@GetMapping("/comments/{commentId}")
	public CommentDTO getComment(@PathVariable int commentId) {
		return new CommentDTO(commentService.getSingle((commentId)));
	}
	
	@PostMapping("/comments")
	public CommentDTO saveComment(@RequestBody CommentDTO commentDTO) {
		Comment theComment = new Comment(commentDTO);
		
		theComment.setId(0);
		commentService.save(theComment);
		
		return new CommentDTO(theComment);
	}
	
	@PutMapping("/comments")
	public CommentDTO updateComment(@RequestBody CommentDTO commentDTO) {
		Comment tempComment = commentService.getSingle(commentDTO.getCommentId());
		
		if (tempComment == null) {
			throw new RuntimeException("No such comment");
		}
		
		Comment theComment = new Comment(commentDTO);
		
		commentService.save(theComment);
		
		return new CommentDTO(theComment);
	}
	
	@DeleteMapping("comments/{commentId}")
	public String deleteLog(@PathVariable int commentId) {
		Comment tempComment = commentService.getSingle(commentId);
		
		if (tempComment == null) {
			throw new RuntimeException("No such comment");
		}
		
		commentService.delete(commentId);
		
		return "Deleted comment with id - " + commentId;
	}
}
