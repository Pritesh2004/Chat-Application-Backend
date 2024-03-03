package com.chatApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	private UserService service;

	// UserController class
	@PostMapping("/user/")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws UserRegistrationException {
	    User newUser = null;
		try {
			newUser = service.registerUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {

		return ResponseEntity.ok(service.getUserByUsername(username));
	}

	@PostMapping("/user/login")
	public ResponseEntity<User> loginUser(@RequestBody UserLogin user) throws Exception {

		User newUser = service.userLogin(user);
		
		if(newUser == null) {
			System.out.print("User Not Present Authentication Failed!");
			return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);

		}
    	return new ResponseEntity<>(newUser,HttpStatus.ACCEPTED); 


	}
	
	 @PutMapping("/user/offline/{username}")
	    public ResponseEntity<User> updateStatus(@PathVariable String username, @RequestBody User updatedUser) {
	        User updated = service.updateStatus(username, updatedUser);
	        if (updated != null) {
	        	System.out.println("Status updated");
	            return new ResponseEntity<>(updated, HttpStatus.OK);
	        } else {
	        	System.out.println("Status not updated");
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
}
