package com.chatApp.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	
	private String fullName;
	
	private String email;
	
	private Status status;
	
	private String password;
	
	
}
