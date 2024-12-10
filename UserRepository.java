package com.example.repositories;


import com.example.entities.User;  //imports my entity
import org.springframework.data.jpa.repository.JpaRepository;  //imports JR interface
import org.springframework.stereotype.Repository;  //imports Repository annotation from Spring Frmework
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//custom method so it can find a user by their username
	Optional<User> findByUsername(String username);

}
