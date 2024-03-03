package com.chatApp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	


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

private boolean isValidUser(User user) {
    // Perform more robust validation as needed
    return !user.getUsername().isEmpty() && !user.getPassword().isEmpty();
}
	
	
	//Get user by username
	public User getUserByUsername(String username) {
		
		User user = userRepository.findByUsername(username);
		return user;
	}
	
	
	//Login the user
	public User userLogin(UserLogin userDetail) {
		
		User user = userRepository.findByUsername(userDetail.getUsername());
		if(user!=null) {
			if(user.getUsername().equals(userDetail.getUsername()) && user.getPassword().equals(userDetail.getPassword())){
				
				user.setStatus(Status.ONLINE);
				userRepository.save(user);
				System.out.print("Authentication successfull");
				System.out.print(user.getUsername()+" hello "+user.getPassword());
				return user;
			}
			System.out.print("Authentication failed");
			return null;
		}
		return user;
	}
	
	 public User updateStatus(String username, User updatedUser) {
	        // Retrieve the existing user
	        User existingUser = userRepository.findByUsername(username);

	        // Update user details if the user exists
	        if (existingUser != null) {
	            existingUser.setStatus(Status.OFFLINE);
	          
	            return userRepository.save(existingUser);
	        }

	        return null;
	    }

}
