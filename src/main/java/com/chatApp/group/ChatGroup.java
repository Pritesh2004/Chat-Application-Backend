package com.chatApp.group;

import java.util.List;

import com.chatApp.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    @Column(unique = true)  // Ensures that groupName is unique
	private String groupName;
	
	@ManyToMany(mappedBy = "groups")
    private List<User> users;
	
}
