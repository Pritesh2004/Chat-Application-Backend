package com.chatApp.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/group")
public class GroupController {

	
	@Autowired
	private GroupService groupService;
	
	@PostMapping("/")
	public ResponseEntity<ChatGroup> createGroup(@RequestBody ChatGroup group){
		
		ChatGroup newGroup = groupService.createGroup(group);
		
		if(newGroup!=null) {
			
			return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
		}
		else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ChatGroup>> getAllGroups(){
		
		List<ChatGroup> listGroups = groupService.getAllGroups();
			
		return ResponseEntity.ok(listGroups);
	
		
	}
}
