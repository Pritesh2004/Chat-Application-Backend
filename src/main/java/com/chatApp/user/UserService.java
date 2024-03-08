package com.chatApp.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.friends.FriendRequest;
import com.chatApp.friends.FriendRequestRepo;
import com.chatApp.group.ChatGroup;
import com.chatApp.group.GroupRepo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendRequestRepo friendRepo;

	@Autowired
	private GroupRepo groupRepo;

// UserService class
	public User registerUser(User user) throws UserRegistrationException {
		User local = userRepository.findByUsername(user.getUsername());

		if (local != null) {
			throw new UserRegistrationException("User already exists");
		} else {
			if (isValidUser(user)) {
				user.setStatus(Status.OFFLINE);
				return userRepository.save(user);
			} else {
				throw new UserRegistrationException("Invalid user details");
			}
		}
	}

	public User addFriend(String username1, String username2) {

		User user1 = userRepository.findByUsername(username1);
		User user2 = userRepository.findByUsername(username2);

		user1.getFriends().add(user2);
		user2.getFriends().add(user1);
		userRepository.save(user2);
		userRepository.save(user1);

		FriendRequest request1 = friendRepo.findBySenderUsernameAndReceiverUsername(username1, username2);
		FriendRequest request2 = friendRepo.findBySenderUsernameAndReceiverUsername(username2, username1);

		if (request1 != null) {

			request1.setStatus(true);

			friendRepo.save(request1);
		}

		if (request2 != null) {

			request2.setStatus(true);

			friendRepo.save(request2);
		}

		return user2;
	}

	public User addUserToGroup(String groupName, String username) {

		ChatGroup group = groupRepo.findByGroupName(groupName);
		User user = userRepository.findByUsername(username);

		user.getGroups().add(group);

		return userRepository.save(user);
	}

	private boolean isValidUser(User user) {
		// Perform more robust validation as needed
		return !user.getUsername().isEmpty() && !user.getPassword().isEmpty();
	}

	// Get user by username
	public User getUserByUsername(String username) {

		User user = userRepository.findByUsername(username);
		return user;
	}

	// Login the user
	public User userLogin(UserLogin userDetail) {

		User user = userRepository.findByUsername(userDetail.getUsername());
		if (user != null) {
			if (user.getUsername().equals(userDetail.getUsername())
					&& user.getPassword().equals(userDetail.getPassword())) {

				user.setStatus(Status.ONLINE);
				userRepository.save(user);
				System.out.println("Authentication successfull");
				return user;
			}
			System.out.print("Authentication failed");
			return null;
		}
		return user;
	}

	public User updateStatus(String username, String status) {
		// Retrieve the existing user
		User existingUser = userRepository.findByUsername(username);

		// Update user details if the user exists
		if (existingUser != null) {
			existingUser.setStatus(Status.valueOf(status));

			return userRepository.save(existingUser);
		}

		return null;
	}

	public List<User> getAllUsers(String username) {

		return userRepository.findByUsernameNot(username);
	}

	public List<User> getAllFriends(Long user_id) {

		return userRepository.findFriendsByUserId(user_id);
	}

}
