package com.chatApp.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.friends.RequestDto;
import com.chatApp.group.AddUserToGroupDto;
import com.chatApp.group.ChatGroup;
import com.chatApp.group.GroupService;

@RestController
@CrossOrigin(origins = "*") // Adjust the origin to match your Angular application URL
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

	@PostMapping("/user/addFriend")
	public ResponseEntity<User> addFriend(@RequestBody RequestDto request) {
		User user = null;
		try {
			user = service.addFriend(request.getSenderUsername(), request.getReceiverUsername());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ResponseEntity<>(user, HttpStatus.CREATED);

	}
	
	@PostMapping("/user/addUserToGroup")
	public ResponseEntity<User> addUserToGroup(@RequestBody AddUserToGroupDto dto){
		
		User updatedUser = service.addUserToGroup(dto.getGroupName(),dto.getUsername());
		
		if(updatedUser!=null) {
			
			return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
		}
		else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}	

	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {

		return ResponseEntity.ok(service.getUserByUsername(username));
	}

	@GetMapping("/user/all/{username}")
	public ResponseEntity<List<User>> getAllUsers(@PathVariable("username") String username) {

		return ResponseEntity.ok(service.getAllUsers(username));
	}

	@GetMapping("/user/getFriends/{user_id}")
	public ResponseEntity<List<User>> getAllFriends(@PathVariable("user_id") Long user_id) {

		return ResponseEntity.ok(service.getAllFriends(user_id));
	}

	@PostMapping("/user/login")
	public ResponseEntity<User> loginUser(@RequestBody UserLogin user) throws Exception {

		User newUser = service.userLogin(user);

		if (newUser == null) {
			System.out.print("User Not Present Authentication Failed!");
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

		}
		return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);

	}
	
	@CrossOrigin(origins = "*") 
	@PostMapping("/user/logout")
	public ResponseEntity<User> updateStatus(@RequestBody UpdateStatusDto dto) {
		User updated = service.updateStatus(dto.getUsername(), "OFFLINE");
		if (updated != null) {
			System.out.println("Status updated");
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} else {
			System.out.println("Status not updated");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
