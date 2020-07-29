package com.looking_glass_consulting.log_server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.looking_glass_consulting.log_server.entity.User;
import com.looking_glass_consulting.log_server.service.DbService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserRestController {

	@Autowired
	private DbService<User> userService;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = userService.get();
		
		return users;
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.getSingle(userId);
	}
	
	@PostMapping("/users")
	public User saveUser(@RequestBody User theUser) {
		theUser.setId(0);
		userService.save(theUser);
		
		System.out.println(theUser.toString());
		
		return theUser;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User theUser) {
		User tempUser = userService.getSingle(theUser.getId());
		
		if (tempUser == null) {
			throw new RuntimeException("No such user");
		}
		userService.save(theUser);
		
		return theUser;
	}
	
	@DeleteMapping("/users/{userId}")
	public User deleteLog(@PathVariable int userId) {
		User tempUser = userService.getSingle(userId);
		
		if (tempUser == null) {
			throw new RuntimeException("No such user");
		}
		
		userService.delete(userId);
		
		return tempUser;
	}
}
