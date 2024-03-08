package com.chatApp.friends;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FriendService {
	
	@Autowired
	private FriendRequestRepo friendRepo;

	public FriendRequest sendRequest(String senderUsername, String receiverUsername) throws FriendRequestException {
		
		FriendRequest request = friendRepo.findBySenderUsernameAndReceiverUsername(senderUsername, receiverUsername);
		
		if(request!=null) {
	        throw new FriendRequestException("Request already exists");

		}
		else {
			FriendRequest newRequest = new FriendRequest();
			
			newRequest.setReceiverUsername(receiverUsername);
			newRequest.setSenderUsername(senderUsername);
			newRequest.setStatus(false);
			
			friendRepo.save(newRequest);
			
			return newRequest;
		}
	}
	
	public List<FriendRequest> getPendingRequest(String username){
		
		List<FriendRequest>  pendingRequests= friendRepo.findByReceiverUsernameAndStatus(username, false);
		
		return pendingRequests;
	}
	
	public FriendRequest getRequestBySenderUsername(String senderUsername, String receiverUsername) {
		
		FriendRequest request = friendRepo.findBySenderUsernameAndReceiverUsername(senderUsername, receiverUsername);
		
		if(request == null) {
			return null;
		}
		else {
			return request;
		}
	}
	
	
}
