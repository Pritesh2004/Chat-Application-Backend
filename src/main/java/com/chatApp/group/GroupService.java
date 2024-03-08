package com.chatApp.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.user.User;
import com.chatApp.user.UserRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepo groupRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public ChatGroup createGroup(ChatGroup group) {
		
		ChatGroup local = groupRepo.findByGroupName(group.getGroupName());
		
		if(local==null) {
			return groupRepo.save(group);
		}
		return null;
	}
	
	
	
	public List<ChatGroup> getAllGroups(){
		
		return groupRepo.findAll();
	}
}
