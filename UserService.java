package com.example.services;


import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired; //inject dependencies automatically
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	//gets all the users
	public List<User> getAllUsers() {
		return userRepository.findAll(); 
	}
	
	
	//retrieves one user by their ID
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	
    //retrieve one user by their username
	public Optional<User> findUserByname(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	
	//create or update user
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	
	
	//delete user by their id
   public void deleteUser(Long id) {
        if(userRepository.existsById(id)){
    	  userRepository.deleteById(id);
    	 } else { throw new IllegalArgumentException("Error - This User hasn't been found");
    		 
    	 }
   }
      }
