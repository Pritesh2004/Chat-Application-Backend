package com.chatApp.friends;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long>{
	
	public FriendRequest findBySenderUsernameAndReceiverUsername(String sUsername, String rUsername);

	public List<FriendRequest> findByReceiverUsernameAndStatus(String receiverUsername,  boolean status);
	
	public List<FriendRequest> findBySenderUsername(String senderUsername);
	
	public void deleteBySenderUsernameAndReceiverUsername(String sUsername, String rUsername);

}
