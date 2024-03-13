package com.chatApp.group.request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRequestRepo extends JpaRepository<GroupRequest, Long>{
	
	public GroupRequest findByGroupAdminUsernameAndUsernameAndGroupName(String groupAdminUsername, String username, String groupName);
	
	public List<GroupRequest> findByGroupAdminUsernameAndIsAccepted(String groupAdminUsername, boolean isAccepted);

	public GroupRequest findByUsernameAndGroupName(String username, String groupName);

}
