package com.chatApp.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	
	@MessageMapping("/message")
	@SendTo("/topic/return-to")
	public Message sendMessage(@RequestBody Message message) {
		
		try {
			Thread.sleep(2000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		return message;
	}
}