package com.chatApp.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	public User findByUsername(String username);
	
	public List<User> findByUsernameNot(String username);
	
	 @Query("SELECT u.friends FROM User u JOIN u.friends f WHERE u.id = :userId")
	    List<User> findFriendsByUserId(@Param("userId") long userId);
	
}
