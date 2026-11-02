package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		return userService.registerUser(user);

	}

	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password) {
		return userService.loginUser(username, password);

	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return userService.getUser(id);

	}

	@GetMapping("/all")
	public List<User> findAllUsers() {
		return userService.getAllUsers();

	}
}
