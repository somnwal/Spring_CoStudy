package com.main.costudy.rest;

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

import com.main.costudy.domain.user.UserVO;
import com.main.costudy.service.user.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/test")
	public String hello() {
		return "USER TEST";
	}
	
	@GetMapping("/user")
	public List<UserVO> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping("/user")
	public UserVO addUser(@RequestBody UserVO user) {
		user.setId(0);
		
		userService.saveUser(user);
		
		return user;
	}
	
	@GetMapping("/user/{id}")
	public UserVO getUser(@PathVariable int id) {
		UserVO user = userService.getUser(id);
		
		if(user == null) {
			throw new RuntimeException("User Not Found : id = " + id);
		}
		
		return user;
	}
	
	@PutMapping("/user")
	public UserVO updateUser(@RequestBody UserVO user) {
		userService.saveUser(user);
		
		return user;
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable int id) {
		UserVO user = userService.getUser(id);
		
		if(user == null) {
			throw new RuntimeException("User Not Found : id = " + id);
		}
		
		userService.deleteUser(id);
		
		return "Deleted User : id = " + id;
	}
}
