package com.chatApp.group.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.friends.FriendRequest;
import com.chatApp.friends.FriendRequestException;

@Service
public class GroupRequestService {

	@Autowired
	private GroupRequestRepo requestRepo;

	public GroupRequest sendGroupRequest(String groupAdminUsername, String username, String groupName) throws GroupRequestException {
		
		GroupRequest request = requestRepo.findByGroupAdminUsernameAndUsernameAndGroupName(groupAdminUsername, username, groupName);
		
		if(request!=null) {
	        throw new GroupRequestException("Request already exists");

		}
		else {
			GroupRequest newRequest = new GroupRequest();
			
			newRequest.setGroupAdminUsername(groupAdminUsername);
			newRequest.setUsername(username);
			newRequest.setGroupName(groupName);
			newRequest.setAccepted(false);
			
			requestRepo.save(newRequest);
			
			return newRequest;
		}
	}

	public List<GroupRequest> getRequestByAdminUsername(String groupAdminUsername){
		
		return requestRepo.findByGroupAdminUsernameAndIsAccepted(groupAdminUsername, false);
	}
	
}
