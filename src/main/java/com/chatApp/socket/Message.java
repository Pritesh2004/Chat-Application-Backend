package com.chatApp.socket;

import java.util.List;

import com.chatApp.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	
	private String username;
	
	private String messageContent;

}
