package com.example.controllers;


import com.example.entities.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;   //inject dependencies automatically
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController   //handles http requests
@RequestMapping("/api/users")  //maps http requests to the class
public class UserController {

	@Autowired   //injects the object into the class, simplifies wiring up dependency
	private UserService userService;
	
	
	//get all the users
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	
	//get user by ID
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	
	//register a new user
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	
	//update a users info
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		return userService.saveUser(user);
	}
	
	
	//delete a user
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "User has been deleted";
	}
}
