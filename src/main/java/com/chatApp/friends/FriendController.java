package com.chatApp.friends;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins="*")
public class FriendController {

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private FriendRequestRepo friendRepo;
	
	 @PostMapping("/friend/send-request")
	    public ResponseEntity<FriendRequest> sendFriendRequest(@RequestBody RequestDto friendRequestDTO) {
	        try {
	            FriendRequest newRequest = friendService.sendRequest(
	                    friendRequestDTO.getSenderUsername(),
	                    friendRequestDTO.getReceiverUsername()
	            );
	            return new ResponseEntity<>(newRequest, HttpStatus.OK);
	        } catch (FriendRequestException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	 

	    @GetMapping("/friend/pending-requests/{username}")
	    public ResponseEntity<List<FriendRequest>> getPendingRequests(@PathVariable String username) {
	        List<FriendRequest> pendingRequests = friendService.getPendingRequest(username);

	        if (pendingRequests.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(pendingRequests, HttpStatus.OK);
	        }
	    }
	 
	    @GetMapping("/friend/friendRequest/{sUsername}/{rUsername}")
	    public ResponseEntity<FriendRequest> getFriendRequest(@PathVariable String sUsername, @PathVariable String rUsername) {
	        try {
	            FriendRequest request = friendService.getRequestBySenderUsername(sUsername, rUsername);
	            if (request == null) {
	                return ResponseEntity.noContent().build();
	            } else {
	                return ResponseEntity.ok(request);
	            }
	        } catch (Exception e) {
	            // Log the exception or handle it appropriately
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	    
	    @DeleteMapping("/friend/deleteRequest/{sUsername}/{rUsername}")
	    @Transactional
	    public ResponseEntity<Void> removeFriendRequest(
	            @PathVariable String sUsername,
	            @PathVariable String rUsername) {

	        friendRepo.deleteBySenderUsernameAndReceiverUsername(sUsername, rUsername);

	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }


}
