package com.chatApp.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

	
	@GetMapping("/messages")
	public ResponseEntity<MessageDto> privateMessage(
			@AuthenticationPrincipal(expression = "email") String email){
		return ResponseEntity.ok(new MessageDto("private content"+email));
	}
}
