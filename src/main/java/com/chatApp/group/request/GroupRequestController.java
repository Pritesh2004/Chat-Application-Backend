package com.chatApp.group.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chatApp.friends.FriendRequest;

@Controller
@RequestMapping("/groupRequest")
@CrossOrigin(origins = "*")
public class GroupRequestController {

	@Autowired
	private GroupRequestService requestService;;

	@PostMapping("/")
	public ResponseEntity<GroupRequest> sendGroupRequest(@RequestBody GroupRequestDto requestDto) {

		GroupRequest request;
		try {
			request = requestService.sendGroupRequest(requestDto.getGroupAdminUsername(), requestDto.getUsername(), requestDto.getGroupName());
			return new ResponseEntity<>(request, HttpStatus.CREATED);

		} catch (GroupRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}
	
	
	@GetMapping("/getGroupRequest/{aUsername}")
	public ResponseEntity<List<GroupRequest>> getAllRequestByAdmin(@PathVariable String aUsername){
		
		 List<GroupRequest> pendingRequests = requestService.getRequestByAdminUsername(aUsername);

	        if (pendingRequests.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(pendingRequests, HttpStatus.OK);
	        }
		
	}
}
