package com.chatApp.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

	@GetMapping("/public/messages")
	public ResponseEntity<MessageDto> publicMessage(){
		return ResponseEntity.ok(new MessageDto("public content"));
	}
}
